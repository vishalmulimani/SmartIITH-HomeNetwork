int vcc = 8; 
int trig = 9; 
int echo = 10; 
int gnd = 11;

void setup()
{
  Serial.begin(9600);
 pinMode (vcc,OUTPUT);
  pinMode (gnd,OUTPUT);
  pinMode(2, OUTPUT);
  digitalWrite(2, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(1000);              // wait for a second
  digitalWrite(2, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);     
   pinMode(1, OUTPUT);
  digitalWrite(1, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(1000);              // wait for a second
  digitalWrite(1, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);  
 pinMode(3, OUTPUT); 
  pinMode(4, OUTPUT);
pinMode(5, OUTPUT);
}

void UltraSonic()
{

  digitalWrite(vcc, HIGH);
  long duration, inches, cm;
  pinMode(trig, OUTPUT);
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  delayMicroseconds(5);
  digitalWrite(trig, LOW);


  pinMode(echo,INPUT);
  duration = pulseIn(echo, HIGH);

  inches = microsecondsToInches(duration);
  cm = microsecondsToCentimeters(duration);
  /*Serial.print("Distance : ");
  Serial.print(" ");
  Serial.print(inches);
  Serial.print("in, ");
  Serial.print(cm);
  Serial.print("cm");
  Serial.println();*/
}



long microsecondsToInches(long microseconds)
{
  microseconds = microseconds/74/2;
  return microseconds;
}

long microsecondsToCentimeters(long microseconds)
{
  microseconds = microseconds/29/2;
  return microseconds;
}

void loop()
{
  
   digitalWrite(vcc, HIGH);
  long duration, inches, cm;
  pinMode(trig, OUTPUT);
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  delayMicroseconds(5);
  digitalWrite(trig, LOW);


  pinMode(echo,INPUT);
  duration = pulseIn(echo, HIGH);
  UltraSonic();
      delay(1000);
  int x = 0;
  x =microsecondsToCentimeters(duration) ;
  Serial.println(x);
  if(0 < x < 10)
   {
       digitalWrite(3, HIGH);
         digitalWrite(4, HIGH);
      digitalWrite(5, HIGH);
   }
   delay(250); //delay 1/4 seconds.
}

