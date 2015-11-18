/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package medilive.sudaapps.net.medilive.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import medilive.sudaapps.net.medilive.model.MedicineSchedule;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "medilive_db";

	// Login table name
	private static final String TABLE_LOGIN = "login";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_GENDER = "gender";
	private static final String KEY_DOB = "dob";
    private static final String KEY_OCC = "occ";
	private static final String KEY_phone_no = "phone_no";
	private static final String KEY_location = "location";

	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";

	private Context mContext;
	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext=context;
	}

	String CREATE_LOGIN_TABLE = "CREATE TABLE " +
			TABLE_LOGIN + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," +
			KEY_UID + " TEXT," +
			KEY_NAME + " TEXT,"
			+ KEY_EMAIL + " TEXT UNIQUE," +
			KEY_GENDER + " TEXT UNIQUE," +
			KEY_DOB + " TEXT UNIQUE," +
			KEY_phone_no + " TEXT UNIQUE," +
			KEY_location + " TEXT UNIQUE," +
			KEY_CREATED_AT + " TEXT" + ")";


	private static final String TABLE_SCHEDULE_MED="schedule_table";
	private static final String KEY_SCHEDULE_MED_ID="_id";
	private static final String KEY_SCHEDULE_MED_NAME="schedule_name";
	private static final String KEY_SCHEDULE_MED_DOZE="schedule_doze";
	private static final String KEY_SCHEDULE_MED_Quantity="schedule_quantity";
	private static final String KEY_SCHEDULE_MED_COMMENT="schedule_comment";
	private static final String KEY_SCHEDULE_MED_START_DATE="schedule_start_date";
	private static final String KEY_SCHEDULE_MED_END_DATE="schedule_end_date";
	private static final String KEY_SCHEDULE_MED_TIME="schedule_time";
	private static final String KEY_SCHEDULE_MED_COMPLETE="schedule_done";
	private static final String KEY_SCHEDULE_MED_ALARM_HOUR="schedule_day_hour";
	private static final String KEY_SCHEDULE_MED_ALARM_MINUTE="schedule_day_minute";




	String CREATE_SCHEDULE_TABLE="CREATE TABLE " +
			TABLE_SCHEDULE_MED + "("
			+ KEY_SCHEDULE_MED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			KEY_SCHEDULE_MED_NAME + " TEXT," +
			KEY_SCHEDULE_MED_DOZE + " TEXT,"
			+ KEY_SCHEDULE_MED_Quantity + " INTEGER ," +
			KEY_SCHEDULE_MED_COMMENT + " TEXT ," +
			KEY_SCHEDULE_MED_START_DATE + " TEXT ," +
			KEY_SCHEDULE_MED_END_DATE + " TEXT ," +
			KEY_SCHEDULE_MED_TIME + " TEXT ," +
			KEY_SCHEDULE_MED_COMPLETE + " INTEGER ," +
			KEY_SCHEDULE_MED_ALARM_HOUR + " INTEGER ," +
			KEY_SCHEDULE_MED_ALARM_MINUTE+ " INTEGER" + ")";


	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_LOGIN_TABLE);
		db.execSQL(CREATE_SCHEDULE_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

//		instead of droping we have to copy these details for backup of user
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_MED);

		// Create tables again
		onCreate(db);
	}



	public void addMedicineSchedule(MedicineSchedule medicineSchedule){
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_SCHEDULE_MED_COMMENT,medicineSchedule.getComment());
			values.put(KEY_SCHEDULE_MED_COMPLETE,medicineSchedule.getIsScheduleEnd());
			values.put(KEY_SCHEDULE_MED_DOZE,medicineSchedule.getDosage());
			values.put(KEY_SCHEDULE_MED_END_DATE,medicineSchedule.getEndDate().getTime().toString());
			values.put(KEY_SCHEDULE_MED_NAME,medicineSchedule.getMedName());
			values.put(KEY_SCHEDULE_MED_START_DATE,medicineSchedule.getStartDate().getTime().toString());
			values.put(KEY_SCHEDULE_MED_TIME,medicineSchedule.getAlarmTime().getTime().toString());
			values.put(KEY_SCHEDULE_MED_Quantity, medicineSchedule.getQuantity());
			values.put(KEY_SCHEDULE_MED_ALARM_HOUR,medicineSchedule.getHour());
			values.put(KEY_SCHEDULE_MED_ALARM_MINUTE,medicineSchedule.getMinutes());

			long id = db.insert(TABLE_SCHEDULE_MED, null, values);
			db.close(); // Closing database connection
			Log.d(TAG, "New schedule inserted into sqlite: " + id);
		}catch (NullPointerException e){
			e.printStackTrace();
			Toast.makeText(mContext,"Schedule not added.",Toast.LENGTH_LONG).show();
		}


	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String gender,String dob, String phone_no, String location, String uid, String created_at) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_GENDER,gender);
		values.put(KEY_DOB,dob);
		values.put(KEY_phone_no,phone_no);
		values.put(KEY_location,location);
		values.put(KEY_UID, uid); // Email
		values.put(KEY_CREATED_AT, created_at); // Created At

		// Inserting Row
		long id = db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	public ArrayList<MedicineSchedule> getMedicineSchedules(){
		ArrayList<MedicineSchedule> medicineSchedules = new ArrayList<>();
		String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE_MED;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			MedicineSchedule medicineSchedule= new MedicineSchedule();
			medicineSchedule.setMedName(cursor.getString(1));
			medicineSchedule.setDosage(cursor.getString(2));
			medicineSchedule.setQuantity(cursor.getInt(3));
			medicineSchedule.setComment(cursor.getString(4));
			medicineSchedule.setStartDate(converStringToCalender(cursor.getString(5)));
			medicineSchedule.setEndDate(converStringToCalender(cursor.getString(6)));
			medicineSchedule.setTime(converStringToCalender(cursor.getString(7)));
			medicineSchedule.setIsScheduleEnd(cursor.getInt(8));
			medicineSchedule.setHour(cursor.getInt(9));
			medicineSchedule.setMinutes(cursor.getInt(10));
			if(medicineSchedule.getIsScheduleEnd()==0)
				medicineSchedules.add(medicineSchedule);

		}

		cursor.close();
		db.close();
		return medicineSchedules;
	}

	private Calendar converStringToCalender(String time){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		try {
			cal.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

	public void updateSchedules(MedicineSchedule medicineSchedule){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SCHEDULE_MED_COMMENT,medicineSchedule.getComment());
		values.put(KEY_SCHEDULE_MED_COMPLETE,medicineSchedule.getIsScheduleEnd());
		values.put(KEY_SCHEDULE_MED_DOZE,medicineSchedule.getDosage());
		values.put(KEY_SCHEDULE_MED_END_DATE,medicineSchedule.getEndDate().getTime().toString());
		values.put(KEY_SCHEDULE_MED_NAME,medicineSchedule.getMedName());
		values.put(KEY_SCHEDULE_MED_START_DATE,medicineSchedule.getStartDate().getTime().toString());
		values.put(KEY_SCHEDULE_MED_TIME,medicineSchedule.getAlarmTime().getTime().toString());
		values.put(KEY_SCHEDULE_MED_Quantity, medicineSchedule.getQuantity());

		String[] args = new String[]{medicineSchedule.getMedName()};
		long id = db.update(TABLE_SCHEDULE_MED, values,KEY_SCHEDULE_MED_NAME + "=?" , args);
		db.close(); // Closing database connection

		Log.d(TAG, "old schedule updated into sqlite: " + id);
	}
	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
            user.put("gender", cursor.getString(3));
            user.put("dob", cursor.getString(4));
            user.put("phone_no", cursor.getString(5));
            user.put("location", cursor.getString(6));
			user.put("uid", cursor.getString(7));
			user.put("created_at", cursor.getString(8));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
