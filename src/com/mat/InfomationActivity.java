/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 * Completion Date: December 15, 2012
 * 
 * Description: Activity class displays fundamental stock information 
 *
 ******************************************************************************/
package com.mat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InfomationActivity extends Activity implements OnClickListener {

	private String Name;
	private String Ticker;
	private String ClosingPrice;
	private String ChangeDollar;
	private String DailyHigh;
	private String DailyLow;
	private String DayMA;
	private String Ratio;
	private String Volume;
	private String WeekHigh;
	private String WeekLow;
	private String ChangePercent;
	
	private Button btnShowNews;
	private Button btnShowChart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infomation);
		getStockIntent();
		bindingStock();
		btnShowChart = (Button)findViewById(R.id.btnShowChart);
		btnShowNews = (Button)findViewById(R.id.btnShowNews);
		btnShowChart.setOnClickListener(this);
		btnShowNews.setOnClickListener(this);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_infomation, menu);
		return true;
	}
	
	private void getStockIntent()
	{
		Bundle extras = getIntent().getExtras();
		Name = extras.getString(StockSQLiteDatabaseActivity.NAME_KEY);
		Ticker = extras.getString(StockSQLiteDatabaseActivity.TICKER_KEY);
		ClosingPrice = extras.getString(StockSQLiteDatabaseActivity.CLOSING_PRICE_KEY);
		ChangeDollar = extras.getString(StockSQLiteDatabaseActivity.CHANGE_DOLLAR_KEY);
		DailyHigh = extras.getString(StockSQLiteDatabaseActivity.DAILY_HIGH_KEY);
		DailyLow = extras.getString(StockSQLiteDatabaseActivity.DAILY_LOW_KEY);
		DayMA = extras.getString(StockSQLiteDatabaseActivity.DAY_MA_KEY);
		Ratio = extras.getString(StockSQLiteDatabaseActivity.RATIO_KEY);
		Volume = extras.getString(StockSQLiteDatabaseActivity.VOLUME_KEY);
		WeekHigh = extras.getString(StockSQLiteDatabaseActivity.WEEK_HIGH_KEY);
		WeekLow = extras.getString(StockSQLiteDatabaseActivity.WEEK_LOW_KEY);
		ChangePercent = extras.getString(StockSQLiteDatabaseActivity.CHANGE_PERCENT_KEY);
	}
	
	public void onClick(View v) 
    {
		
    	switch(v.getId()) 
    	{
    	case R.id.btnShowNews:
    		Intent intentNews = new Intent(this, NewsActivity.class);
    		startActivity(intentNews);
    		break;
    	case R.id.btnShowChart:
    		Intent intentChart = new Intent(this, ChartActivity.class);
    		startActivity(intentChart);
    		break;
    	default:
    		break;
    	}
    }

	private void bindingStock()
	{
		TextView txtName = (TextView)findViewById(R.id.txtName);
		TextView txtClosingPrice = (TextView)findViewById(R.id.txtClosingPrice);
		TextView txtChangeDollar = (TextView)findViewById(R.id.txtChangeDollar);
		TextView txtDailyHigh = (TextView)findViewById(R.id.txtDailyHigh);
		TextView txtDailyLow = (TextView)findViewById(R.id.txtDailyLow);
		TextView txtDayMA = (TextView)findViewById(R.id.txtDayMA);
		TextView txtRatio = (TextView)findViewById(R.id.txtRatio);
		TextView txtVolume = (TextView)findViewById(R.id.txtVolume);
		TextView txtWeekHigh = (TextView)findViewById(R.id.txtWeekHigh);
		TextView txtWeekLow = (TextView)findViewById(R.id.txtWeekLow);
		TextView txtChangePercent = (TextView)findViewById(R.id.txtChangePercent);
		
		txtName.setText(Name + " (" + Ticker + ")");
		txtClosingPrice.setText(ClosingPrice);
		txtChangeDollar.setText(ChangeDollar);
		txtDailyHigh.setText(DailyHigh);
		txtDailyLow.setText(DailyLow);
		txtDayMA.setText(DayMA);
		txtRatio.setText(Ratio);
		txtVolume.setText(Volume);
		txtWeekHigh.setText(WeekHigh);
		txtWeekLow.setText(WeekLow);
		txtChangePercent.setText(ChangePercent);
	}
}
