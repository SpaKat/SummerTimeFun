// NeoPixel Ring simple sketch (c) 2013 Shae Erisson
// released under the GPLv3 license to match the rest of the AdaFruit NeoPixel library

#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif

// Which pin on the Arduino is connected to the NeoPixels?
// On a Trinket or Gemma we suggest changing this to 1
#define SIDEPIN           6
#define POLEPIN            7

// How many NeoPixels are attached to the Arduino?
#define SIDEPIXELS     14
#define POLEPIXELS     21
// When we setup the NeoPixel library, we tell it how many pixels, and which pin to use to send signals.
// Note that for older NeoPixel strips you might need to change the third parameter--see the strandtest
// example for more information on possible values.
Adafruit_NeoPixel sidePixels = Adafruit_NeoPixel(SIDEPIXELS, SIDEPIN, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel polePixels = Adafruit_NeoPixel(POLEPIXELS, POLEPIN, NEO_GRB + NEO_KHZ800);

void setup() {
  Serial.begin(9600);
  sidePixels.begin(); // This initializes the NeoPixel library.
  polePixels.begin();
  delay(100);
  
}

void loop() {

int color = Serial.read();
int i;

  for(i=0;i<POLEPIXELS;i++)
  {
    // pixels.Color takes RGB values, from 0,0,0 up to 255,255,255
   
    switch(color)
    {
      case 0:
         polePixels.setPixelColor(i, polePixels.Color(75,0,0)); // Moderately bright red color.
        break;
      case 1:
        polePixels.setPixelColor(i, polePixels.Color(75,75,0)); // Moderately bright yellow color.
        break;
      case 2:
        polePixels.setPixelColor(i, polePixels.Color(0,75,0)); // Moderately bright green color.
        break;
       case 3:
        polePixels.setPixelColor(i, polePixels.Color(0,0,0)); // Moderately bright blank color.
        break;
    }
    
   }

 polePixels.show(); // This sends the updated pixel color to the hardware

for(i=0;i<SIDEPIXELS;i++)
  {
    // pixels.Color takes RGB values, from 0,0,0 up to 255,255,255
   
    switch(color)
    {
      case 0:
         sidePixels.setPixelColor(i, sidePixels.Color(75,0,0)); // Moderately bright red color.
        break;
      case 1:
        sidePixels.setPixelColor(i, sidePixels.Color(75,75,0)); // Moderately bright yellow color.
        break;
      case 2:
        sidePixels.setPixelColor(i, sidePixels.Color(0,75,0)); // Moderately bright green color.
        break;
       case 3:
        sidePixels.setPixelColor(i, sidePixels.Color(0,0,0)); // Moderately bright blank color.
        break;
    }
    
   }

 sidePixels.show(); // This sends the updated pixel color to the hardware
     
  delay(50); // Delay for a period of time (in milliseconds).
 
}


