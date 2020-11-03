package samir.alakbarov.lagrangecalculator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Scanner;

import io.github.kexanie.library.MathView;


public class MainActivity extends AppCompatActivity {
    
    private double[][] cavab;
    private double cmex;
    private EditText et1;
    private EditText et2;
    private InterstitialAd interstitialAd;
    private double mex;
    private double[] mexrec;
    private int one;
    private int say;
    private double[] soncavab;
    private double[] sonrec;
    private String str = "";
    private SwitchMaterial sw1;
    private MathView tv1;
    private double[] yarr;
    private int yfor;
    
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        
        interstitialAd = new InterstitialAd(this);
        MobileAds.initialize((Context) this, initializationStatus -> {
        });
        
        AdView adView = findViewById(R.id.adView);
        Button bt1 = findViewById(R.id.bt1);
        tv1 = findViewById(R.id.tv1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        sw1 = findViewById(R.id.switch1);
        
        et1.requestFocus();
        
        String testAdUnitId = "ca-app-pub-3940256099942544/1033173712";
        interstitialAd.setAdUnitId("ca-app-pub-4567612668621445/5200901778");
        interstitialAd.loadAd(new Builder().build());
        adView.loadAd(new Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAd.loadAd(new Builder().build());
            }
        });
        
        tv1.config("MathJax.Hub.Config({\n  CommonHTML: { linebreaks: { automatic: true } },\n  \"HTML-CSS\": { linebreaks: { automatic: true } },\n         SVG: { linebreaks: { automatic: true } }\n});");
        
        
        
        bt1.setOnClickListener((View.OnClickListener) view -> {
            
            int i;
            String str = "$$";
            
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                Log.e("Ad", "Ad is not loaded!!!");
            }
            
            int iterator = 0;
            String e1 = String.valueOf(et1.getText());
            String e2 = String.valueOf(et2.getText());
            say = 0;
            cmex = 1.0d;
            one = 0;
            Scanner sc = new Scanner(e1).useDelimiter(" ");
            while (sc.hasNextDouble()) {
                sc.nextDouble();
                say = say + 1;
            }
            
            cavab = new double[say][say];
            mexrec = new double[say];
            double[] xarr = new double[say];
            yarr = new double[say];
            soncavab = new double[say];
            sonrec = new double[say];
            mex = 1.0d;
            double[][] ints = new double[say][say];
            
            Scanner sc2 = new Scanner(e2).useDelimiter(" ");
            while (sc2.hasNextDouble()) {
                yarr[iterator] = yarr[iterator] + sc2.nextDouble();
                iterator++;
            }
            Scanner sc1 = new Scanner(e1).useDelimiter(" ");
            iterator = 0;
            while (sc1.hasNextDouble()) {
                xarr[iterator] = xarr[iterator] + sc1.nextDouble();
                iterator++;
            }
            Scanner sc12;
            Scanner sc22;
            if (xarr.length == yarr.length) {
                int r;
                double[] xarr2;
                say = 1;
                for (r = 0; r < say; r++) {
                    mexrec[r] = 1.0d;
                    ints[r][0] = 1.0d;
                }
                yfor = 0;
                while (yfor < say) {
                    r = 0;
                    while (r < say) {
                        if (r != yfor) {
                            sc12 = sc1;
                            mex *= xarr[yfor] - xarr[r];
                            i = iterator;
                            e1 = e1;
                            ints[yfor][say] = -xarr[r];
                            say++;
                        } else {
                            i = iterator;
                            sc12 = sc1;
                            e1 = e1;
                        }
                        r++;
                        e1 = e1;
                        iterator = i;
                        sc1 = sc12;
                    }
                    i = iterator;
                    sc12 = sc1;
                    sonrec = ints[yfor];
                    mexrec[yfor] = mex;
                    xarr2 = xarr;
                    cmex *= mex;
                    rec(say, -1, 1.0d, 0);
                    one = one + 1;
                    say = 1;
                    mex = 1.0d;
                    yfor = yfor + 1;
                    xarr = xarr2;
                    iterator = i;
                    sc1 = sc12;
                }
                
            }
            
        });
        
        
    }
    
    
    public boolean isInteger(double digit) {
        int first = (int) digit;
        double convertedDouble = first * 1.0;
        return convertedDouble == first;
    }
    
    
    public void rec(int row, int f, double ed, int sira) {
        row--;
        if (row == 0) {
            int i = 0;
            while (true) {
                int i2 = say;
                if (i < i2) {
                    if (sira < i || (sira == 0 && f == i2 - 2)) {
                        double[] dArr;
                        if (i == 0) {
                            dArr = cavab[one];
                            int i3 = f + 1;
                            dArr[i3] = dArr[i3] + ((sonrec[i] * ed) * yarr[yfor]);
                        } else {
                            dArr = cavab[one];
                            dArr[f] = dArr[f] + ((sonrec[i] * ed) * yarr[yfor]);
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
        for (int i4 = 0; i4 < say; i4++) {
            if (sira < i4 || sira == 0) {
                if (i4 == 0) {
                    rec(row, f + 1, sonrec[i4] * ed, i4);
                } else {
                    rec(row, f, ed * sonrec[i4], i4);
                }
            }
        }
    }
}
