package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

/* The CrimeLab class is the control layer responsible 
 * for managing all crime data. It contains a list
 * of Crime class objects used to store this data
 * and can save the data to a file. 
 */
public class CrimeLab {
	public static final String TAG = "CrimeLab";
	public static final String FILENAME = "crimes.json";
	
	private CriminalIntentJSONSerializer mSerializer;
	
	private ArrayList<Crime> mCrimes;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private CrimeLab(Context appContext){
		mAppContext = appContext;
		
		mSerializer = new CriminalIntentJSONSerializer(mAppContext,FILENAME);
		try {
			mCrimes = mSerializer.loadCrimes();
		} catch (Exception e) {
			mCrimes = new ArrayList<Crime>();
			Log.e(TAG, "Error loading crimes ",e);
		}
	}
	
	/* Since the CrimeLab is the interface 
	 * to updating all crimes it is best to provide
	 * the save to file interface here
	 */
	public boolean saveCrimes(){
		try{
			mSerializer.saveCrimes(mCrimes);
			Log.d(TAG, "Saved crimes to file");
			return true;
		} catch (Exception e) { /* Might want a Toast for the user */
			Log.e(TAG, "Error saving crimes ",e);
			return false;
		}
	}
	
	public void addCrime(Crime c){
		mCrimes.add(c);
	}
	
	public static CrimeLab get(Context c){
		if(sCrimeLab == null){
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public Crime getCrime(UUID id){
		for(Crime c : mCrimes){
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}

}
