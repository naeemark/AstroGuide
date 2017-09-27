## Home _`(MVP) ` `(Level-1) `_ ##

An Activity to be used for two puposes. To show list of;
- Channels
- Favourite Channels

#### Main Functionality ####

- Holds a `Navigation Drawer` to provide links to other areas of the application
- Loads List of channels from `API`
- Show Favourite button for each row of the list
- Shows Floating ActionButton to show the already marked Favourites List
- Prompts if user is not logged in when taps on Favourite button
- Uses **cache** to hold data after a successful response

#### Construction Elements / Dependencies ####

- Module to provide Dagger2 support
- Component
- Interactor
- Presenter
- XML layout

##### Presenter #####

- Provides `Interface` and implementation to manipulate UI and functionality for the Activity.
- Uses Decoupled classes for Functionality and Ui

##### Interactor #####

- Provides `interface` implementation of Use-cases, i.e. `OnFetchDataListener`


#### Propogates Changes ####

- By clicking a row, goes to `Details Activity` 
- By clicking on `On Air` fron Navigation Drawer, goes to `OnAirActivity`
- By clicking on `Login` fron Navigation Drawer, goes to `LoginActivity`
- By clicking on `logout` fron Navigation Drawer, goes to `LoginActivity`
- By clicking on `About` fron Navigation Drawer, shows About dialog
