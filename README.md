[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)



# Introducing "ChroniclePlanner" - The Ultimate Journal!

## Introduction
- Get a great overview of your week with a beautifully designed personal journal. Our application (JavaFX GUI) presents each day of the 7-day week, allowing you to plan and visualize your schedule.
- Create new Events and Tasks directly from the Week view. Specify essential details such as name, description, day of the week, start time, duration, and task completion status. Never miss a beat with our intuitive input editing capabilities; just don’t forget to save it so you can always return to your personalized journal!
- Stay on track with your commitments, and don’t overload yourself with the help of our commitment warnings. If the number of tasks or events exceeds the maximum limit set for each day, ChroniclePlanner will promptly notify you, ensuring a well-balanced workload.

## Headlining Features
- Keep your tasks organized and accessible throughout the week with our intuitive sidebar. Effortlessly manage your tasks from any screen within the app, ensuring a smooth workflow. 
- Now you can also personalize your ChroniclePlanner experience by choosing from a set of three themes - Default, Red, and Blue.

## Power-Ups
- Refresh your memory of an event Event or check out your Task more thoroughly by opening them in a dedicated window, providing a view of all associated details. 
- Stay informed and track your progress with our Weekly Overview. Gain valuable statistics about your week.
- Visualize your daily progress with our intuitive progress bar. Track the number of tasks completed versus planned for each day, clearly representing your accomplishments and upcoming tasks.

## Quality of Life
- Experience convenience as ChroniclePlanner automatically detects and makes any valid HTTP/HTTPS link within a description clickable. 
- With ChroniclePlanner, you can edit any aspect of any existing Event or Task directly from the Week view. Stay agile and adjust your schedule effortlessly.

## Special features (extra credit)
- Always feel safe as your journal is now password protected.
- Every time you feel like journaling, start your work on a good note with our beautiful Splash Screen every time you open the application!

                                Download now and discover the great ChroniclePlanner!
![Screen Shot 2023-06-22 at 2 06 06 AM](https://github.com/CS-3500-OOD/pa05-aoun-worshippers-1/assets/126209831/f2360231-1003-42a9-bd68-f1345144d89d)
![Screen Shot 2023-06-22 at 2 05 37 AM](https://github.com/CS-3500-OOD/pa05-aoun-worshippers-1/assets/126209831/583df3e0-8287-4a02-bfb6-9dcb95a8b8e5)



# SOLID

## S
Our project uses the Single Responsibility Principle, ensuring that every class in our code serves a distinct purpose. For instance, our Event and Task classes are solely responsible for encapsulating information associated with tasks and events, such as names, descriptions, dates, durations, etc. 

## O
We have tried to keep our code open for extension but closed for modification by following the Open/Closed Principle. Classes like Event and Task are designed to be extensible without modifying existing code. This is achieved using inheritance and composition, enabling us to introduce new types of tasks or events, like a future task or rare event, without affecting the base classes.

## L
The Liskov Substitution Principle is not very present in our project. As the code is structured, we allow a parent class to be replaced by its child class without altering our code’s functionality. For example, if we would implement a new special event class which would be a subclass of Event, we could replace the Event class within the program without breaking anything. Even though this is not being widely implemented, it allows us to use Liskov Substitution Principle if needed.

## I
The Interface Segregation Principle is done by our separation of interfaces based on each class's needs. Our MVC structure enhances this principle's application; instead of one huge interface containing all methods, we have interfaces such as JournalView, JournalModel, TaskPopup, and JournalController, each created for specific interactions between the Model, View, and Controller.

## D
Lastly, we tried to follow the Dependency Inversion Principle to maintain the independence of high-level classes from low-level classes. For example, our code uses a TaskPopup interface to create pop-ups for any class object, not just specific ones like Event or Task, but also for popups used to save, choose or create bujo files, etc. This interface represents a form of abstraction that allows any class object to become a pop-up, making it possible to modify or replace different parts in our code without disrupting functionality. Also, many GUI elements get information directly from the model, so affecting the model would affect all those elements automatically.



# How we would extend our program to add an additional, non-implemented Feature

## If we added Section 3's "Sort by Name & Duration"
If we were to add a sorting feature to sort Events and Tasks by either Name or Duration, we would start by implementing the Comparable interface to our Event and Task classes, which would enable sorting based on name and duration. Then we would simply add a compareTo() method in each class that compares the objects' names or durations.

After implementing the sorting function in our code, we would introduce a sorting option in the GUI, allowing the user to select (between two buttons) either "Sort by Name" or "Sort by Duration." To make this work, we would proceed by implementing event handlers for both sorting scenarios that will use the user’s input (presses on buttons) that will then trigger the sorting process.

When the event handling is done, we would need a method within the controller class. This method would be responsible for communication between the GUI and the model. So, when the user then selects a sorting option, we call this method to initiate the sorting process.

Lastly, when the sorting is finished, the GUI is updated to reflect the new order of events and tasks. This would most likely be done by manipulating the scene to refresh the user's view so that the application displays the new, sorted list. 

# Attribution
If we were to use an image to make our buttons for the sorting options look informative such as the following examples taken from the web, we would have to give credit if the images are copyrighted or such:

![buttonOOD](https://github.com/CS-3500-OOD/pa05-aoun-worshippers-1/assets/126209831/eb14249c-4587-4c91-a22a-3f982ac018df) 


![button2OOD](https://github.com/CS-3500-OOD/pa05-aoun-worshippers-1/assets/126209831/0327988c-6585-49fc-806b-6cc91bc76f81)


To give credit where credit is due, we would ensure that we find the source of an image or any copyrighted material we use in our design. To then actually distribute the credit, we would utilize our GUI to display who or what should be mentioned. This would be in a section we call “Credit to authors“ which would include: the title of the image, the image creator's name, the date when the image was created, and the copyrighted date so no disputes, legal issues, or unclarities arise which could leave to the end of our Journal application. 
# Bullet-Journal
# Bullet-Journal
