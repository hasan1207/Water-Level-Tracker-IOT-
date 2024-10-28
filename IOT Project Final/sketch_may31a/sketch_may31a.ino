void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  for(int i = 2; i < 6; i++){
    pinMode(i, INPUT_PULLUP);
  }
}


String ranges[] = {"25-75", "75-125", "125-175", "175-225", "225+"};


int waterLevel(){
    int i = 2;
    while(digitalRead(i) == 0 && i < 6){
      i++;
    }
    return i;



}

void loop() {
  // put your main code here, to run repeatedly:
  int level = waterLevel() - 2;

  Serial.print(ranges[level]);
  //Serial.print("\n");

  // level = random(0,5);
  // Serial.print(level);
  // Serial.print("\n");

  //Serial.print(level);
  //Serial.print(random(0,5));
  //Serial.print(ranges[random(0,5)]);
  Serial.print("\n");
  

  delay(1000);
}
