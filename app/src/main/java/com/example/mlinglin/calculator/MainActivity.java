package com.example.mlinglin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mlinglin.calculator.customView.ButtonView;
import com.example.mlinglin.calculator.customView.CalculatorViewGroup;
import com.example.mlinglin.calculator.utils.DensityUtil;
import com.example.mlinglin.calculator.utils.StringToArithmetic;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView str;       //显示屏
    private StringBuilder stringBuilder;    //显示的字符

    /**
     * 用数组存储每个button的名字和ID，利用循环赋值
     */
    private String[] buttonName = {"AC","(", ")", "/",
                                    "7", "8", "9", "*",
                                    "4", "5", "6", "-",
                                    "1", "2", "3", "+",
                                    "<-", "0", ".", "="};
    private int[] buttonId = {R.id.clear, R.id.left, R.id.right, R.id.div,
                            R.id.seven, R.id.eight, R.id.nine, R.id.mul,
                            R.id.four, R.id.five, R.id.six, R.id.sub,
                            R.id.one, R.id.two, R.id.three, R.id.add,
                            R.id.delete, R.id.zero, R.id.point, R.id.getResult};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        stringBuilder = new StringBuilder();
        str = findViewById(R.id.showNum);
        CalculatorViewGroup calculatorViewGroup = findViewById(R.id.buttonLayout);
        for (int i = 0; i < buttonName.length; i++){
            ButtonView button = new ButtonView(this);
            button.setText(buttonName[i]);  //设置控件内文字
            button.setId(buttonId[i]);      //设置控件Id
            button.setOnClickListener(this);    //设置控件点击事件
            calculatorViewGroup.addView(button, DensityUtil.dip2px(this, 80), DensityUtil.dip2px(this, 80));
            //添加到布局当中
        }
    }


    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getResult:
                if (stringBuilder != null && stringBuilder.length() != 0) {
                    if (judgeEqual(stringBuilder)) {
                        str.setText(String.valueOf(StringToArithmetic.stringToArithmetic(stringBuilder.toString())));
                        stringBuilder.delete(0, stringBuilder.length());
                    }else{
                        str.setText("错误");
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                } else {
                    str.setText("");
                }
                break;
            case R.id.clear:
                if (stringBuilder == null || stringBuilder.length() == 0){
                    str.setText("");
                    break;
                } else {
                    stringBuilder = stringBuilder.delete(0, stringBuilder.length());
                    str.setText(stringBuilder);
                }
                break;
            case R.id.left:
                stringBuilder = stringBuilder.append("(");
                str.setText(stringBuilder);
                break;
            case R.id.right:
                if (judgeRightBracket(stringBuilder)) {
                    stringBuilder = stringBuilder.append(")");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.point:
                if (judgePoint(stringBuilder)) {
                    stringBuilder = stringBuilder.append(".");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.delete:
                if (stringBuilder == null || stringBuilder.length() == 0){
                    break;
                } else {
                    stringBuilder = stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                    str.setText(stringBuilder);
                }
                break;
            case R.id.div:
                if (judgeOperator(stringBuilder)) {
                    stringBuilder = stringBuilder.append("/");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.mul:
                if (judgeOperator(stringBuilder)) {
                    stringBuilder = stringBuilder.append("*");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.add:
                if (judgeOperator(stringBuilder)) {
                    stringBuilder = stringBuilder.append("+");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.sub:
                if (judgeOperator(stringBuilder)) {
                    stringBuilder = stringBuilder.append("-");
                    str.setText(stringBuilder);
                }
                break;
            case R.id.seven:
                stringBuilder = stringBuilder.append("7");
                str.setText(stringBuilder);
                break;
            case R.id.eight:
                stringBuilder = stringBuilder.append("8");
                str.setText(stringBuilder);
                break;
            case R.id.nine:
                stringBuilder = stringBuilder.append("9");
                str.setText(stringBuilder);
                break;
            case R.id.four:
                stringBuilder = stringBuilder.append("4");
                str.setText(stringBuilder);
                break;
            case R.id.five:
                stringBuilder = stringBuilder.append("5");
                str.setText(stringBuilder);
                break;
            case R.id.six:
                stringBuilder = stringBuilder.append("6");
                str.setText(stringBuilder);
                break;
            case R.id.one:
                stringBuilder = stringBuilder.append("1");
                str.setText(stringBuilder);
                break;
            case R.id.two:
                stringBuilder = stringBuilder.append("2");
                str.setText(stringBuilder);
                break;
            case R.id.three:
                stringBuilder = stringBuilder.append("3");
                str.setText(stringBuilder);
                break;
            case R.id.zero:
                stringBuilder = stringBuilder.append("0");
                str.setText(stringBuilder);
                break;
            default:
                break;
        }

    }

    /**
     * 判断小数点过多、StringBuilder为空、前面无数字情况
     * @param s
     * @return
     */
    public boolean judgePoint(StringBuilder s){
        //如果StringBuilder为空，则添加0
        if (s == null || s.length() == 0){
            s.append("0");
            return true;
        }
        //如果前一个是小数点，则返回false不添加
//        for (int i = 0; i < s.length(); i++){
//            if (String.valueOf(s.charAt(i)).equals(".")){
//                return false;
//            }
        if (String.valueOf(s.charAt(s.length() - 1)).equals(".")){
            return false;
        }
        //如果前面是运算符（+-*/），则为0.X
        if (String.valueOf(s.charAt(s.length() - 1)).equals("+") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("-") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("*") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("/")){
            s.append("0");
            return true;
        }
        return true;
    }

    /**
     * 判断运算符过多、前面无数字、StringBuilder为空情况
     * @param s
     * @return
     */
    public boolean judgeOperator(StringBuilder s){
        //如果StringBuilder为空，则不添加
        if (s == null || s.length() == 0){
            return false;
        }
        //如果前一个字符为运算符，则替换前一个运算符
        if (String.valueOf(s.charAt(s.length() - 1)).equals("+") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("-") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("*") ||
                String.valueOf(s.charAt(s.length() - 1)).equals("/")){
            s.delete(s.length() - 1, s.length());
            return true;
        }
        return true;
    }

    /**
     * 判断右括号
     * @param s
     * @return
     */
    public boolean judgeRightBracket(StringBuilder s){
        int countLeft = 0;
        int countRight = 0;
        //如果StringBuilder为空，则不添加
        if (s == null || s.length() == 0){
            return false;
        }
        //如果前面没有出现左括号则不添加
        for (int i = 0; i < s.length(); i++){
            if (String.valueOf(s.charAt(i)).equals("(")){
                countLeft ++;
            }
            if (String.valueOf(s.charAt(i)).equals(")")){
                countRight ++;
            }
        }
        if (countLeft == 0){
            return false;
        }
        //判断匹配左右括号数量，保证右括号不会多
        if (countLeft == countRight){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断等号，无数字则返回false，/号后带0则返回false
     * @param s
     * @return
     */
    public boolean judgeEqual(StringBuilder s){
        int countNum = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9){
                countNum ++;
            }
            if (String.valueOf(s.charAt(i)).equals("/") && s.charAt(i + 1) - '0' == 0){
                return false;
            }
        }
        if (countNum == 0){
            return false;
        } else{
            return true;
        }
    }

}
