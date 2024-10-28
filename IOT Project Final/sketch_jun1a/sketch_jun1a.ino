#include<ESP8266WiFi.h>
#include<WiFiClient.h>
#include<ESP8266HTTPClient.h>

String URL="http://api.thingspeak.com/update?api_key=6H5RRKTPA5JGNEYY";

void setup() {
  // put your setup code here, to run once:

  Serial.begin(115200);
  WiFi.disconnect();
  delay(2000);
  Serial.print("Start connection");
  WiFi.begin("Hafeez_TP-Link","bwk8y5fo");
  while((!(WiFi.status()== WL_CONNECTED))){
      delay(200);
      Serial.print("..");
    }
  Serial.println("Connected");





}

void sendWaterData(int level){
  WiFiClient client;
  HTTPClient http;

  String newUrl=URL+ "&field1=" + level;
  http.begin(client,newUrl);
  int responsecode=http.GET();
  String data=http.getString();
  //Serial.println(level);
  http.end();
  

}

String ranges[] = {"25-75", "75-125", "125-175", "175-225", "225+"};

void loop() {
  // put your main code here, to run repeatedly:
  //  float t = random(20,40);
  //  float h = random(0,100);

  //  sendData(t,h);
  if(Serial.available() > 0){
    String data = Serial.readStringUntil('\n');
    Serial.print(data);
    Serial.print("\n");
    //sendWaterData(data);
    int rangesSize = sizeof(ranges) / sizeof(ranges[0]);
    for(int i = 0; i < rangesSize; i++){
      if(data == ranges[i]){
        sendWaterData(i);
        //Serial.print(i);
        //Serial.print("\n");
      }
    }
    
  }

  //sendWaterData("160");


   delay(1000);

}