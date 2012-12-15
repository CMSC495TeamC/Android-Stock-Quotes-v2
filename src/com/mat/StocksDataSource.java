/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 * Completion Date: December 15, 2012
 * 
 * Description: Class creates a new database and open() and close() methods. 
 * This class also contains the method for creating and inserting a Stock object
 * into the database.
 *
 ******************************************************************************/
package com.mat;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StocksDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private StockDatabaseHelper dbHelper;
	  private String[] allColumns = { StockDatabaseHelper.KEY_ID, StockDatabaseHelper.KEY_TICKER, StockDatabaseHelper.KEY_NAME, 
			  StockDatabaseHelper.KEY_CLOSING_PRICE, StockDatabaseHelper.KEY_DAILY_DOLLAR_CHANGE, StockDatabaseHelper.KEY_DAILY_PERCENT_CHANGE,
			  StockDatabaseHelper.KEY_DAILY_HIGH, StockDatabaseHelper.KEY_DAILY_LOW, StockDatabaseHelper.KEY_52_WEEK_HIGH,
			  StockDatabaseHelper.KEY_52_WEEK_LOW, StockDatabaseHelper.KEY_PE_RATIO, StockDatabaseHelper.KEY_VOLUME, 
			  StockDatabaseHelper.KEY_50_DAY_MOVING_AVERAGE };

	  public StocksDataSource(Context context) {
	    dbHelper = new StockDatabaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Stock createStock(Stock stock) {
	    ContentValues values = new ContentValues();
	    values.put(StockDatabaseHelper.KEY_TICKER, stock.getTicker());
	    values.put(StockDatabaseHelper.KEY_NAME, stock.getName());
	    values.put(StockDatabaseHelper.KEY_CLOSING_PRICE, stock.getClosingPrice());
	    values.put(StockDatabaseHelper.KEY_DAILY_DOLLAR_CHANGE, stock.getDailyDollarChange());
	    values.put(StockDatabaseHelper.KEY_DAILY_PERCENT_CHANGE, stock.getDailyPercentChange());
	    values.put(StockDatabaseHelper.KEY_DAILY_HIGH, stock.getDailyHigh());
	    values.put(StockDatabaseHelper.KEY_DAILY_LOW, stock.getDailyLow());
	    values.put(StockDatabaseHelper.KEY_52_WEEK_HIGH, stock.get52WeekHigh());
	    values.put(StockDatabaseHelper.KEY_52_WEEK_LOW, stock.get52WeekLow());
	    values.put(StockDatabaseHelper.KEY_PE_RATIO, stock.getPEratio());
	    values.put(StockDatabaseHelper.KEY_VOLUME, stock.getVolume());
	    values.put(StockDatabaseHelper.KEY_50_DAY_MOVING_AVERAGE, stock.get50DayMovingAverage());
	    
	    long insertId = database.insert(StockDatabaseHelper.TABLE_STOCKS, null,
	        values);
	    
	    Cursor cursor = database.query(StockDatabaseHelper.TABLE_STOCKS,
	        allColumns, StockDatabaseHelper.KEY_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Stock newStock = cursorToStock(cursor);
	    cursor.close();
	    return newStock;
	  }

//	  public void deleteStock(Comment comment) {
//	    long id = comment.getId();
//	    System.out.println("Comment deleted with id: " + id);
//	    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
//	        + " = " + id, null);
//	  }
//
//	  public List<Stock> getAllStocks() {
//	    List<Comment> comments = new ArrayList<Comment>();
//
//	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
//	        allColumns, null, null, null, null, null);
//
//	    cursor.moveToFirst();
//	    while (!cursor.isAfterLast()) {
//	      Comment comment = cursorToComment(cursor);
//	      comments.add(comment);
//	      cursor.moveToNext();
//	    }
//	    // Make sure to close the cursor
//	    cursor.close();
//	    return comments;
//	  }

	  private Stock cursorToStock(Cursor cursor) {
	    /*Stock stock = new Stock(cursor.getInt(0), cursor.getString(1), 
	    		cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4), 
	    		cursor.getString(5), cursor.getString(6), cursor.getString(7), 
	    		cursor.getString(8), cursor.getString(9), cursor.getInt(10), 
	    		cursor.getString(11), cursor.getString(12));
	    return comment;*/
		  return new Stock();
	  }
	}