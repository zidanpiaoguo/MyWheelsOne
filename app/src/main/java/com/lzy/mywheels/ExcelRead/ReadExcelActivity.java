package com.lzy.mywheels.ExcelRead;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lzy.mywheels.App.MyToast;
import com.lzy.mywheels.R;

import java.io.FileInputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcelActivity extends AppCompatActivity {


    @BindView(R.id.txt_show)
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_excel);
        ButterKnife.bind(this);
        readExcel();
    }


    public void readExcel() {
        try {
            /**
             * 后续考虑问题,比如Exel里面的图片以及其他数据类型的读取
             **/
            MyToast.makeText("进来了");
            String path = Environment.getExternalStorageDirectory().getPath();
            InputStream is = new FileInputStream(path+"/test.xls");
            MyToast.makeText("找到了");
            //Workbook book = Workbook.getWorkbook(new File("mnt/sdcard/test.xls"));
            Workbook book = Workbook.getWorkbook(is);

            Log.d("lzy","sdfsdf");
            int num = book.getNumberOfSheets();
            txt.setText("the num of sheets is " + num + "\n");
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            txt.append("the name of sheet is " + sheet.getName() + "\n");
            txt.append("total rows is " + Rows + "\n");
            txt.append("total cols is " + Cols + "\n");
            for (int i = 0; i < Cols; ++i) {
                for (int j = 0; j < Rows; ++j) {
                    // getCell(Col,Row)获得单元格的值
                    txt.append("contents:" + sheet.getCell(i, j).getContents() + "\n");
                }
            }
            book.close();
        } catch (Exception e) {
            System.out.println(e);

        }
    }
}
