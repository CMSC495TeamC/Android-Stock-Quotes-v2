/*******************************************************************************
 *         Student: Mathew Yamasaki
 *          Course: Current Trends and Projects in Computer Science (CMSC 495)
 * Completion Date: November 28, 2012
 * 
 * Description: This class retrieves stock quote information from Yahoo! Finance
 * and parses the input stream to create a new Stock object.
 * 
 ******************************************************************************/
package com.mat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/*
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
*/

public class YahooFinanceDataHelper {

    private static final String CHART_URL = "http://chart.finance.yahoo.com/z?s=GOOG&t=6m&q=l&l=on&z=l&p=m50,m200 ";
    
    private static final String HISTORICAL_DATA = "http://ichart.yahoo.com/table.csv?s=GOOG&a=0&b=1&c=2000&d=0&e=31&f=2010&g=d&ignore=.csv";
    
    /*
    private static final String SP500_LIST = "MMM+ACE+AES+AFL+GAS+T+ABT+ANF+ACN+ADBE+AMD+"
            + "AET+A+APD+ARG+AKAM+AA+ALXN+ATI+AGN+ALL+ALTR+MO+AMZN+AEE+AEP+AXP+AIG+AMT+"
            + "AMP+ABC+AMGN+APH+APC+ADI+AON+APA+AIV+APOL+AAPL+AMAT+ADM+AIZ+AN+AZO+ADSK+"
            + "ADP+AVB+AVY+AVP+BBT+BMC+BHI+BLL+BAC+BCR+BAX+BEAM+BDX+BBBY+BMS+BRK/B+BBY+"
            + "BIG+BIIB+BLK+HRB+BA+BWA+BXP+BSX+BMY+BRCM+BF/B+CA+CBG+CBS+CF+CHRW+CMS+CNX+"
            + "CSX+CVS+CVC+COG+CAM+CPB+COF+CAH+CFN+KMX+CCL+CAT+CELG+CNP+CTL+CERN+CHK+CVX+"
            + "CME+CMG+CB+CI+CINF+CTAS+CSCO+C+CTXS+CLF+CLX+COH+KO+CCE+CTSH+CL+CMCSA+CMA+CSC+"
            + "CAG+COP+ED+STZ+CBE+GLW+COST+CVH+COV+CCI+CMI+DTV+DTE+DVA+DHR+DRI+DF+DE+DELL+"
            + "DNR+XRAY+DVN+DO+DFS+DISCA+DLTR+D+RRD+DOV+DOW+DPS+DUK+DNB+ETFC+DD+EMC+EOG+EQT+"
            + "EMN+ETN+ECL+EIX+EW+EA+EMR+ESV+ETR+EFX+EQR+EL+EXC+EXPE+EXPD+ESRX+XOM+FFIV+"
            + "FLIR+FMC+FTI+FDO+FAST+FDX+FII+FIS+FITB+FHN+FSLR+FE+FISV+FLS+FLR+F+FRX+FOSL+"
            + "BEN+FCX+FTR+GME+GCI+GPS+GD+GE+GIS+GPC+GNW+GILD+GS+GT+GOOG+GWW+HCP+HAL+HOG+"
            + "HAR+HRS+HIG+HAS+HCN+HNZ+HP+HSY+HES+HPQ+HD+HON+HRL+DHI+HSP+HST+HCBK+HUM+HBAN+"
            + "ITW+IR+TEG+INTC+ICE+IPG+IBM+IFF+IGT+IP+INTU+ISRG+IVZ+IRM+JDSU+JPM+JBL+JEC+"
            + "JNJ+JCI+JOY+JNPR+KLAC+K+KEY+KMB+KIM+KMI+KSS+KRFT+KR+LLL+LSI+LH+LRCX+LM+LEG+"
            + "LEN+LUK+LIFE+LLY+LTD+LNC+LLTC+LMT+L+LO+LOW+LYB+MTB+M+MRO+MPC+MAR+MMC+MAS+MA+"
            + "MAT+MKC+MCD+MHP+MCK+MJN+MWV+MDT+MRK+MET+PCS+MCHP+MU+MSFT+MOLX+TAP+MDLZ+MON+"
            + "MNST+MCO+MS+MOS+MSI+MUR+MYL+NKE+NRG+NYX+NBR+NDAQ+NOV+NTAP+NFLX+NWL+NFX+NEM+"
            + "NWSA+NEE+NI+NE+NBL+JWN+NSC+NU+NTRS+NOC+NUE+NVDA+ORLY+OKE+OXY+OMC+ORCL+OI+"
            + "PCAR+PETM+PCG+PNC+PPG+PPL+PLL+PH+PDCO+PAYX+BTU+JCP+PNR+PBCT+POM+PEP+PKI+PRGO+"
            + "PFE+PM+PSX+PNW+PXD+PBI+PCL+PX+PCP+PCLN+PFG+PLD+PG+PGR+PRU+PEG+PSA+PHM+QEP+"
            + "QCOM+PWR+DGX+RL+RRC+RTN+RHT+RF+RSG+RAI+RHI+ROK+COL+ROP+ROST+RDC+R+SAI+SCG+"
            + "SLM+SWY+CRM+SNDK+SLB+SCHW+SNI+STX+SEE+SRE+SHW+SIAL+SPG+SJM+SNA+SO+LUV+SWN+SE+"
            + "S+STJ+SWK+SPLS+SBUX+HOT+STT+SRCL+SYK+STI+SYMC+SYY+TROW+TEL+TE+TJX+TGT+THC+TDC+"
            + "TER+TSO+TXN+TXT+ADT+BK+WMB+TMO+TIF+TWC+TWX+TIE+TMK+TSS+TRV+TRIP+TYC+TSN+USB+"
            + "UNP+UPS+X+UTX+UNH+UNM+URBN+VFC+VLO+VAR+VTR+VRSN+VZ+VIAB+V+VNO+VMC+WPX+WMT+WAG+"
            + "DIS+WPO+WM+WAT+WPI+WLP+WFC+WDC+WU+WY+WHR+WFM+WIN+WEC+WYN+WYNN+XL+XEL+XRX+XLNX+"
            + "XYL+YHOO+YUM+ZMH+ZION+EBAY";
            * 
            */

    /*
    private static final String SP500_LIST = "MMM+ACE+AES+AFL+GAS+T+ABT+ANF+ACN+ADBE+AMD+"
            + "AET+A+APD+ARG+AKAM+AA+ALXN+ATI+AGN+ALL+ALTR+MO+AMZN+AEE+AEP+AXP+AIG+AMT+"
            + "AMP+ABC+AMGN+APH+APC+ADI+AON+APA";
            * 
            */
            
    private static final String SP500_LIST = "MMM+ACE+AES+AFL+GAS+T+ABT+ANF+ACN+ADBE+AMD+"
            + "AET+A+APD+ARG+AKAM+AA+ALXN+ATI+AGN+ALL+ALTR+MO+AMZN+AEE+AEP+AXP+AIG+AMT+"
            + "AMP+ABC+AMGN+APH+APC+ADI+AON+APA+AIV+APOL+AAPL+AMAT+ADM+AIZ+AN+AZO+ADSK+"
            + "ADP+AVB+AVY+AVP+BBT+BMC+BHI+BLL";
            
    private static final String URL_TAGS = "sl1c1p2kjhgrvm3n";
    
    private static final String QUOTE_URL = "http://finance.yahoo.com/d/quotes.csv?s=" + SP500_LIST + "&f=" + URL_TAGS;
    
    static String ticker = "";
    static double closingPrice = 0.0;
    static double dollarChange = 0.0;
    static String percentChange = "";
    static double dailyHigh = 0.0;
    static double dailyLow = 0.0;
    static double week52High = 0.0;
    static double week52Low = 0.0;
    static double peRatio = 0.0;
    static int volume = 0;
    static double movingAverage50Day = 0.0;
    static String name = "";
    
    public static ArrayList<Stock> stockList;
        
    public static List<Stock> parseResponse() {
     
        BufferedReader br = null;
        Stock s = new Stock();
        stockList = new ArrayList<Stock>();
        
             try {
                URL yahooFinance = new URL(QUOTE_URL);
                URLConnection yc = yahooFinance.openConnection();
                 
                String currentLine;
                br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((currentLine = br.readLine()) != null) {
                    //System.out.println(currentLine);
                    String[] stock = currentLine.split(",", 12);
                    
                    //for (int i = 0; i < stock.length; i++) {

                        ticker = stock[0].replaceAll("\"", "");             // Column 1
                        closingPrice = Double.parseDouble(stock[1]);        // Column 2
                        dollarChange = Double.parseDouble(stock[2]);        // Column 3
                        percentChange = stock[3].replaceAll("\"", "");      // Column 4
                        dailyHigh = Double.parseDouble(stock[4]);           // Column 5
                        dailyLow = Double.parseDouble(stock[5]);            // Column 6
                        week52High = Double.parseDouble(stock[6]);          // Column 7
                        week52Low = Double.parseDouble(stock[7]);           // Column 8
                        
                        if (Pattern.matches("N/A", stock[8]))               // Column 9
                            peRatio = 0;
                        else
                            peRatio = Double.parseDouble(stock[8]);
 
                        volume = Integer.parseInt(stock[9]);                // Column 10
                        
                        movingAverage50Day = Double.parseDouble(stock[10]); // Column 11
                        name = stock[11].replaceAll("\"", "").trim();       // Column 12
                                        
                    
                        s = new Stock(ticker, closingPrice, dollarChange, percentChange, dailyHigh, dailyLow,
                        		week52High, week52Low, peRatio, volume, movingAverage50Day, name);
                    
                        stockList.add(s);
                        
                    //}
                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        return stockList;
    } // end parseResponse
    
}
