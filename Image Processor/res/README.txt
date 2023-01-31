Assignment 6: Image Processing (Part 3)
Alex Hwang & Jalen Wu
Image Citation (cherry): https://gallery.yopriceville.com/var/resizes/Free-Clipart-Pictures/Fruit-PNG/Red_Cherries_PNG_Clipart.png?m=1558398177

During this assignment, we made two major updates from our previous assignment. We added two new classes
called 'SwingFeaturesFrame' and 'Histogram' into the view package. The 'SwingFeaturesFrame' class creates 
a GUI for user to use and interact with. The 'Histogram' class displays the red, green, and blue components
of an image that is chosen. The remaining of the program has been maintained from the previous assignment.

The 'SwingFeaturesFrame' class essentailly renders a view for users to interact with in order to manipulate images
of their choice. On the left hand side, a list of buttons with avilable commands are shown, where the user can
choose which any command after an image is loaded in. The center shows the image that is loaded and ready to be
manipulated. The right hand side displays a histogram of the red, green, and blue components of the image, with
the assistance of the 'Histogram' class. The 'SwingFeaturesFrame' class also implements our IView interface that
was created in previous assignments, so that this new class is integrated into our code, rather than a modification.

The 'Histogram' class is responsible for creating a histogram of the red, green, and blue components of the image
that is loaded or modified. In order to do so, the class overrides the paintComponents() command, and takes in
the image's RGB intensity hashmaps, which document the frequency of each color intesity. There are also additional
methods within our 'Image' class to count the number of pixels and their intensities. All other fuctionality has
been maintained.

---------------------------------------------------------------------------------------------------------------

Assignment 5: Image Processing (Part 2)
Alex Hwang & Jalen Wu
Image Citation (cherry): https://gallery.yopriceville.com/var/resizes/Free-Clipart-Pictures/Fruit-PNG/Red_Cherries_PNG_Clipart.png?m=1558398177
Image Citation (sunflower): https://commons.wikimedia.org/wiki/File:Sunflower_from_Silesia2.jpg

During this assignment, we made several updates from our previous assignments: support the ability to load
and save coventional files formats, which includes bmp, jpg, and png, support the ability to blur and sharpen
an image, and support the ability to produce a sepia color transformation to an image.

In order to support different file formats, we implemented two new methods inside our 'Utils' class. The
'readFile' method reads any image file that the user inputs, including ppm image files. The 'decipherBuffer'
method essetially gets the pixels based on the image file and returns the image, which then can be manipulated
by the user. The 'Utils' class assisted in maniting readibility within our code in order to implment newer
methods, such as reading different file formates besides a ppm file.

In order to support the ability to blur and sharpen an image, we included the caculations in order to either
blur or sharpen the image. Since both types of filtering involved similar caculations and the only difference
was the content within the matrix, both types were under the same command, as it will perform the correct
filtering based on the user. Thus, we added a new 'CommandFilterImpl', which essentially acts the same as
the other CommandImpls.

In order to support the ability to produce a sepia color transformation, since it involved very similar
calculations as the luma greyscale, we put both the luma color transformation and sepia color transformation
under the same method 'colorTransformation', with the only difference being the numbers within the matrix. 
Similar to supporting blur and sharpen, both color transformations were placed under the same comman, as it
will perform the correct color transformation based on the user. Thus, we added a new 'CommandColorTransformationImpl'.

Besides these implementations, the original design has remained essentially the same.

-----------------------------------------------------------------------------------------------------------------------------------

Assignment 4: Image Processing
Alex Hwang & Jalen Wu
Image Citation: https://gallery.yopriceville.com/var/resizes/Free-Clipart-Pictures/Fruit-PNG/Red_Cherries_PNG_Clipart.png?m=1558398177

For this program, we adhered to the MVC design, where the interfaces and classes have been separated into
three different package folders: model, view, and controller. Within the model package, we added another
class called 'Utils', which contains a method (from the 'ImageUtil' class) that helps the model read a PPM
file and provides its data information. This information that is retreived can be easily manipulated by the
model, as well as additional implementations can be made to read other types of files besides a PPM file. The
'ImageUtil' class doesn't fit within any of the three package folders, as this class is used to run the
program using the main method.

Our Model sets the foundation of the program, with two specific classes that act as the main foundational
classes: the 'Pixel' and 'Image' class. The 'Pixel' class represents the individual pixels that make up an
image, while the 'Image' class is an ArrayList of an ArrayList of Pixels to form the entire image. We chose
to set our foundation this way because it was a similar concept that has been used in previous OOD, Fundies 2,
and Fundies 1 assignments, and it is easy to access where you want to mmodify the image (though, you would
want to modify the entire image). There is also the IModel interface, where the user can use the commands
flip, brighten or darken, greyscale, load, and save. These manipulations will be saved to an ArrayList of
Images, where you can access each of the altered images that the user manipulated. The MockModelImpl is only
utilized for testing, so that the user knows if they made any mistakes in their inputs.

Our View simply contains a method, which is in the 'View' class, that renders a message to the user based
on the specifications of the Controller. The view will adapt and present the manipulated images.One should
be able to add more implementations to the View.

Our Controller consists of two interfaces, the 'IController' and 'ICommand' interfaces. The 'IController'
interface essentially processes an image manipulation based on the user's desired command. However, since
each command requires different number of arguments, this is where the 'ICommand' interface comes in, to
ensure that the number or arguments correlates with the user's desired command. There is a class for each
command that implements the 'ICommand' interface, where the Controller class can detect which command to used
based on the user's number of arguments.


In order to run a script using this program, you must run the main method within the 'ImageUtil' class and
write the commands inside the console. Examples of commands that this program accepts to manipulate an
image are:

flip cherry vertical vertical-flip-cherry
save ./res/cherry/vertical-flip-cherry vertical-flip-cherry

flip cherry horizontal horizontal-flip-cherry
save ./res/cherry/horizontal-flip-cherry horizontal-flip-cherry

brighten cherry 20 brighten-20-cherry
save ./red/cherry/brighten-20-cherry brighten-20-cherry

darken cherry -20 darken-(20)-cherry
save ./red/cherry/darken-(20)-cherry darken-(20)-cherry

greyscale cherry red greyscale-red-cherry
save ./red/cherry/greyscale-red-cherry greyscale-red-cherry

greyscale cherry green greyscale-green-cherry
save ./red/cherry/greyscale-green-cherry greyscale-green-cherry

greyscale cherry blue greyscale-blue-cherry
save ./red/cherry/greyscale-blue-cherry greyscale-blue-cherry

greyscale cherry value greyscale-value-cherry
save ./red/cherry/greyscale-value-cherry greyscale-value-cherry

greyscale cherry intensity greyscale-intensity-cherry
save ./red/cherry/greyscale-intensity-cherry greyscale-intensity-cherry

greyscale cherry luma greyscale-luma-cherry
save ./red/cherry/greyscale-luma-cherry greyscale-luma-cherry

load ./res/cherry/cherry.ppm cherry

save ./red/cherry/cherryCopy.ppm cherryCopy