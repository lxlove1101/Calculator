package com.example.mlinglin.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mlinglin.calculator.customView.ButtonView;
import com.example.mlinglin.calculator.customView.CalculatorViewGroup;
import com.example.mlinglin.calculator.utils.StringToArithmetic;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ButtonView> buttonViewList;
    private ButtonView bt1;
    private ButtonView bt2;
    private ButtonView bt3;
    private ButtonView bt4;
    private ButtonView bt5;
    private ButtonView bt6;
    private ButtonView bt7;
    private ButtonView bt8;
    private ButtonView bt9;
    private ButtonView bt10;
    private ButtonView bt11;
    private ButtonView bt12;
    private ButtonView bt13;
    private ButtonView bt14;
    private ButtonView bt15;
    private ButtonView bt16;
    private ButtonView bt17;
    private ButtonView bt18;
    private ButtonView bt19;
    private ButtonView bt20;
    private TextView str;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setOnClick();

    }

    /**
     * 初始化布局
     */
    private void initView(){
        buttonViewList = new ArrayList<>();
        bt1 = new ButtonView(this);
        bt2 = new ButtonView(this);
        bt3 = new ButtonView(this);
        bt4 = new ButtonView(this);
        bt5 = new ButtonView(this);
        bt6 = new ButtonView(this);
        bt7 = new ButtonView(this);
        bt8 = new ButtonView(this);
        bt9 = new ButtonView(this);
        bt10 = new ButtonView(this);
        bt11 = new ButtonView(this);
        bt12 = new ButtonView(this);
        bt13 = new ButtonView(this);
        bt14 = new ButtonView(this);
        bt15 = new ButtonView(this);
        bt16 = new ButtonView(this);
        bt17 = new ButtonView(this);
        bt18 = new ButtonView(this);
        bt19 = new ButtonView(this);
        bt20 = new ButtonView(this);
        str = findViewById(R.id.showNum);
        stringBuilder = new StringBuilder();
        setButtonId();

        bt1.setText("AC");
        buttonViewList.add(bt1);
        bt2.setText("(");
        buttonViewList.add(bt2);
        bt3.setText(")");
        buttonViewList.add(bt3);
        bt4.setText("/");
        buttonViewList.add(bt4);
        bt5.setText("7");
        buttonViewList.add(bt5);
        bt6.setText("8");
        buttonViewList.add(bt6);
        bt7.setText("9");
        buttonViewList.add(bt7);
        bt8.setText("*");
        buttonViewList.add(bt8);
        bt9.setText("4");
        buttonViewList.add(bt9);
        bt10.setText("5");
        buttonViewList.add(bt10);
        bt11.setText("6");
        buttonViewList.add(bt11);
        bt12.setText("-");
        buttonViewList.add(bt12);
        bt13.setText("1");
        buttonViewList.add(bt13);
        bt14.setText("2");
        buttonViewList.add(bt14);
        bt15.setText("3");
        buttonViewList.add(bt15);
        bt16.setText("+");
        buttonViewList.add(bt16);
        bt17.setText("<-");
        buttonViewList.add(bt17);
        bt18.setText("0");
        buttonViewList.add(bt18);
        bt19.setText(".");
        buttonViewList.add(bt19);
        bt20.setText("=");
        buttonViewList.add(bt20);

        CalculatorViewGroup calculatorViewGroup = findViewById(R.id.buttonLayout);
        //循环添加ViewGroup中的View
        for (int i = 0; i < 20; i++) {
            calculatorViewGroup.addView(buttonViewList.get(i), 150, 150);
        }

    }

    /**
     * 设置点击事件
     */
    public void setOnClick(){
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        bt11.setOnClickListener(this);
        bt12.setOnClickListener(this);
        bt13.setOnClickListener(this);
        bt14.setOnClickListener(this);
        bt15.setOnClickListener(this);
        bt16.setOnClickListener(this);
        bt17.setOnClickListener(this);
        bt18.setOnClickListener(this);
        bt19.setOnClickListener(this);
        bt20.setOnClickListener(this);
    }

    /**
     * 设置new控件的id方便实现onClickListener接口
     */
    public void setButtonId(){
        bt1.setId(R.id.clear);
        bt2.setId(R.id.left);
        bt3.setId(R.id.right);
        bt4.setId(R.id.div);
        bt5.setId(R.id.seven);
        bt6.setId(R.id.eight);
        bt7.setId(R.id.nine);
        bt8.setId(R.id.mul);
        bt9.setId(R.id.four);
        bt10.setId(R.id.five);
        bt11.setId(R.id.six);
        bt12.setId(R.id.sub);
        bt13.setId(R.id.one);
        bt14.setId(R.id.two);
        bt15.setId(R.id.three);
        bt16.setId(R.id.add);
        bt17.setId(R.id.delete);
        bt18.setId(R.id.zero);
        bt19.setId(R.id.point);
        bt20.setId(R.id.getResult);

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
