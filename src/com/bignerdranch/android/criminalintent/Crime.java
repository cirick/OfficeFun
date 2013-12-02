package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Charles Irick
 *
 */
public class Crime {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	
	private static final String JSON_ID="id";
	private static final String JSON_TITLE="title";
	private static final String JSON_SOLVED="solved";
	private static final String JSON_DATE="date";
	
	/**
	 * Constructor
	 * Each new object gets a unique UUID and has
	 * an associated date, title, and solved field. 
	 */
	public Crime() {
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
	/**
	 * Constructor
	 * This constructor is used to create a crime
	 * object that was saved to a file. This constructor
	 * takes a JSON object and parses it to retrieve 
	 * the Crime object information. 
	 * 
	 * @param json
	 * @throws JSONException
	 */
	public Crime(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		mTitle = json.getString(JSON_TITLE);
		mSolved = json.getBoolean(JSON_SOLVED);
		mDate = new Date(json.getLong(JSON_DATE));
	}
	
	/**
	 * toJSON
	 * In order to save the information from a Crime
	 * the info is pushed into a JSON object. This 
	 * object is returned to the caller for use in 
	 * storing this information to a file. 
	 * 
	 * @return JSONObject
	 * @throws JSONException
	 */
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_DATE, mDate.getTime());
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE,mTitle);
		json.put(JSON_SOLVED,mSolved);
		return json;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
}
