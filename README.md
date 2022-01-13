# snapcode-dummy-app
The app uses the logic pulled from the Snapchat app to generate Snapcodes (in the form of an SVG string) with particular sets of data. This string is then copied to the device's clipboard (yes, very hacky) to be rendered offline.

I used this to create a mapping between the dots in a Snapcode and the bitstring of the underlying data. Read the full blog post on how I used this mapping to read the contents of Snapcodes here: [link](https://labs.ioactive.com/2021/12/cracking-snapcode.html)



**NOTE:** Almost none of this code is my own creation; the only code I wrote is located in the main activity (src/main/java/com/snap/nloader/MainActivity.java). The structure of this app was generated from the default Android Studio template, which was then filled in using logic and libraries pulled from the decompiled Snapchat Android app.
