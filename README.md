# Cookie Clicker Lab
## Objective
Cookie Clicker! GUI App Development Walkthrough
In this lab you will walk through the process of writing a simplified Cookie Clicker GUI app.
The app starts out like this... 

Pressing the button 15 times... 

And after 20+ button presses...

## Directions
1. Accept the assignment in Google Classroom and run it once it's done loading. You should see this when you run the app:

2. In this step you will change the title of the app, size of the app, and background color of the app.

    In Java, and most languages, the constructor method (see diagram in section 1) is designated for the code that sets up the app you are creating. 
    At run time the Java interpreter calls the constructor method ***exactly once*** at the beginning of the app's life (and never returns to the constructor method again unless you quit the app and you re-run the app).
    
    The `super.` shown on lines 15, 16, and 17 indicate that these methods are defined in the `CookieClicker` superclass (`GBSApp.java`). 
    Your code is calling those methods to customize your app. 
    Change the `white` background to `pink` (or another color of your choice) and change the size of the app to be `300` by `250`.
    Leave line 17 as it is!
    ```java
    // Constructor method: initialize instance variables
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

    }
    ```
    
    Notice that on line 13 of the screenshot above there is a **parameter** of type `JFrame` whose name is `f`. 
    Just like `terminalWindow` has a `setTitle` method to change the title, the `JFrame` class also has a `setTitle` method to change the title.
    
    On line 18 add this line of code: `f.setTitle("Tairy's Bakery");`, but replace Tairy with your own name. 
    When you run the app, you should now see the new title:


3. In the Files pane look inside the `gbs` folder and you will see two image files with a file names that end with `.png` (portable network graphics).
    
    To be able to use `Image`s in our code we need to import the standard library `Image` class which defines a new type named `Image`.
    Add this line of code on line 5 (underneath the other import statements): `import java.awt.Image;`

    To be as efficient as possible at run time, we will load the one `cookie.png` file once from the network and save it in a variable of type `Image`. 
    Because loading the image into memory should only occur once, we will write the code to load an image in the `CookieClicker` constructor.

    In the `CookieClicker` constructor add the code shown on line 19 to load the `.png` file into a variable named `cookie1` of type `Image`.
    ```java
    // Constructor method: initialize instance variables
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

        Image cookie1 = super.getImage("gbs/one cookie.png");
    }
    ```

    Add the code shown on 29 to display the image.
    The `Graphics` class has a `drawImage` method that requires four parameters: the variable name of the image to draw, the (x, y) of where to place the upper-left corner of the image, and the word `this`.
    You will learn the mystery this later in the chapter.
    ```java
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BLACK);
        g.drawImage(cookie1, 120, 80, this);
    }
    ```

    Run the app you will be treated to this syntax error message:
    ```
    CookieClick.java:29: error: cannot find symbol
        g.drawImage(cookie1, 120, 80, this);
                    ^
      symbol:   variable cookie1
      location: class CookieClicker
    1 error
    ```

    Ha! Tricked ya! 
    I deliberately led you astray to help explain something important. 
    The `cookie1` variable was declared on line 19 as a local variable and it goes out of scope (dies) on line 20. 
    The compiler looks at line 29 and realizes that there is no variable named `cookie1` still in scope. 
    You could (but should not) fix this by moving the code from line 19 to just before line 29, but this unnecessarily (and wastefully) reloads the image each time the Java interpreter calls the `paintComponent` method (which happens a lot at run time). 
    The correct way to fix this problem is to use an instance variable.

    Declare an instance variable as shown on line 9. 
    Instance variables can be used in multiple methods (in this app, both the constructor `CookieClicker` method and `paintComponent` method).
    Instance variables retain their value for the life of the app (unlike local variables which lose their value when the method they are declared in completes).

    Run the app and the compile error message goes away, but your app still does not show the cookie :(!

    I deliberately misled you again (last time, I promise) to illustrate a common logic error known as a `shadow` variable. 
    A `shadow` variable is a local variable declared inside a method that also has the same name as an instance variable.

    In our situation the instance variable declared on line 9 is being shadowed by the local variable declared on line 19 because they are both named `cookie1`.

    Line 19 ***should not redeclare*** `cookie1` because the preexisting instance variable `cookie1` (line 9) will not be assigned a value like it should, only the local (shadow) variable `cookie1` (on line 19) will be assigned a value. 
    Again, the `cookie1` declared on line 19 is a local variable and goes out of scope on line 20. 
    However, the `cookie1` variable in the `g.drawImage` on line 29 refers to the instance variable `cookie1` on line 9.

    To fix this problem, remove the word `Image` on line 19. 
    By doing this, the `cookie1` instance variable (declared on line 9) will now correctly be assigned its initial value on line 19. 
    ```java
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

        cookie1 = super.getImage("gbs/one cookie.png"); // <--
    }
    ```
    Rerun the app and you will (finally!) see the cookie image on the app.

4. In this step you will add a button to the app.
    To use buttons in Java we need to `import` the standard library button class which defines a new type named `JButton`.
    Go to the end of line 5 and press the Enter key a few times to create space for another `import` statement on line 6: `import javax.swing.JButton;`.


    ```java
    import java.awt.Image;
    import javax.swing.JButton;
    ```

    Similar to what we did for the image in step #3, declare an instance variable of type `JButton` (line 12) and then assign a value to that instance variable in the constructor method (line 22).
    ```java
    private Image cookie1;
    private JButton moreButton; // <--
    ```
    On line 22 we are calling a constructor method of the `JButton` class which has a parameter of type `String` that will be the text we want displayed on the button.
    ```java
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

        cookie1 = super.getImage("gbs/one cookie.png"); 

        moreButton = new JButton("Moar PLZ!") // <--
        super.add(moreButton);
    }
    ```

    Because a button is interactive and is not an `Image`, we cannot use `g.drawImage` as we did for `cookie1`.
    Instead, we will add it to our app by calling our superclass method `add` (line 23).
    If you fail to include line 23, the button will not appear at run time.

    Run the app and you should see a pressable button at the top center of the app.

5. Now that we have a button on the app, we need to keep track of how many times the user has pressed it at run time.
    The code we need to write to make this happen will be the most sophisticated task thus far because it involves the interplay of an instance variable and all three methods.

    Just as we did for the `Image` and `Jbutton`, declare an instance variable of type `int` (line 13) and 
    ```java
    private Image cookie1;
    private JButton moreButton; 
    private int howMany; // <--
    ```

    Then assign it a value (line 24) in the constructor method.
    ```java
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

        cookie1 = super.getImage("gbs/one cookie.png"); 

        moreButton = new JButton("Moar PLZ!")
        super.add(moreButton);

        howMany = 1; // <--
    }
    ```

    Since we would like the text "1 Cookie!" to occur on the app's screen, add the code shown on line 35.
    ```java
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BLACK);
        g.drawImage(cookie1, 120, 80, this);
        g.drawString(howMany+" Cookie!, 10, 40); // <--
    }
    ```

    When you run the app you should see this:


    However, pressing the button does not seem to do anything useful yet!
    Here is the strategy we will use: each time the user presses the button at run time we will increase the `howMany` instance variable by one. 
    To accomplish this we need to do two things:

    #### Step #1:
    In the constructor method, call the `addActionListener` method on the `moreButton` to tell the Java interpreter what to do at run time when the user presses the button. 
    The newly added line 24 shown at right instructs the Java interpreter to go to the `actionPerformed` method located in this file at run time whenever the `moreButton` is pressed.
    ```java
    public CookieClicker(JFrame f) {
        super.setBackground(WHITE);
        super.setSize(700,500);
        super.autoArrangeGUIForMe(true);

        cookie1 = super.getImage("gbs/one cookie.png"); 

        moreButton = new JButton("Moar PLZ!");
        super.add(moreButton);
        moreButton.addActionListener(this); // <--

        howMany = 1;
    }
    ```

    #### Step #2:
    In the `actionPerformed` method add line 43 to increase the value of the `howMany` instance variable. 
    The code shown on line 45 is a call to the `repaint` method defined in our superclass (`GBSApp`).
    Line 45 calls the `repaint` method of our super class, which in turn will make a call to our very own `paintComponent` method.

    ```java
    @Override
    public void actionPerformed(ActionEvent ae) {
        howMany += 1; // <--

        super.repaint() // <--
    }
    ```

    Line 35 in the `paintComponent` method will redraw the `String` to reflect the increased value of the `howMany` instance variable.
    ```java
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BLACK);
        g.drawImage(cookie1, 120, 80, this);
        g.drawString(howMany+" Cookie!, 10, 40); // <--
    }
    ```

    When you run the app you should see the number of cookies increase every time you press the button.
    
    [add image of # Cookie]()

    

    Let's fix the poor grammar so that it says Cookies (instead of Cookie) whenever there is more than one cookie.

    [add image of # Cookies]()

    ```java
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BLACK);
        g.drawImage(cookie1, 120, 80, this);
        if (howMany == 1) {
            g.drawString(howMany+" Cookie!", 10, 40);
        } else {
            g.drawString(howMany+" Cookies!", 10, 40);
        }
    }
    ```

6. Complete this final task on your own!
    
    A) Declare a second `Image` instance variable.

    B) In the constructor method assign the instance variable from part (A) a value using the many `cookie.png` file.

    C) Show the `cookie1` image just like we have been doing, but whenever the `howMany` instance variable is larger than 20, show the other image instance variable from part (A) instead.

7. Once you done, add and commit your changes to your main branch. Check the Github page to see the autograded results.

    Your teacher may open an Issue if something needs correcting in your code.
