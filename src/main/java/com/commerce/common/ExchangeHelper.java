package com.commerce.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

@Component
public class ExchangeHelper {
    String apiUrl = "https://api.it120.cc/gooking/forex/rate?fromCode=CNY&toCode=AUD"; // 实时汇率接口

    public double formatdouble(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        String formattedNumber = decimalFormat.format(number);
        double formattedDouble = Double.parseDouble(formattedNumber);
        return  formattedDouble;
    }
    private static double getRatesFromJson(String jsonResponse) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonResponse).getAsJsonObject();

        // 获取 rates 字段
        return jsonObject.getAsJsonObject("data").get("rate").getAsDouble();
    }

    public double getExchange() {
        double cnyRate = 4.7;
        try {
            // 创建 URL 对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String jsonResponse = response.toString();
                // 使用 Gson 解析 JSON 数据
                cnyRate = getRatesFromJson(jsonResponse);                // 打印响应数据
                System.out.println("Response: " + cnyRate);
            } else {
                System.out.println("GET request failed with response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnyRate;
    }
}
