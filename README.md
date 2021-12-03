I program the advent of code challenges using Kotlin/Native.

## How to compile?
Generally the `gradle build` task generates the binary file, but to read the input you need to call it with different options. 
Also, you need to set the main file in the build.gradle.kts. I will not go into detail for this.

## Special about Kotlin/Native
Kotlin/Native is not different from normal Kotlin when it comes to the math parts of Advent of Code. However the hardest part (so far) was to load the input. I tried two approaches.

### Load from the web with curl
I used this sample by jetbrains to generate a library which allows me to use curl: https://github.com/kotlin-hands-on/intro-kotlin-native
Then I provided the input on a webserver (Advent of Code requires authentication) and loaded it this way. That workes but I only used it for the
first day

### Load from a resource
On the second day I learned that I can use Okio to read files. I downloaded the input as text file and saved it to resources. Then I added a new task
`gradle assembleWithResources` that copies the resource files and the executable to 'build/ready'. From there one can call the binary 
and pass the fitting resource file name.

### Copy the input into a Kotlin file
Using multiline Strings one could just copy the input and compile it as Kotlin file. That would be boring, so I didn't do it, but it probably works.
