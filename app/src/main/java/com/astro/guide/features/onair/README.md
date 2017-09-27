## On Air _`(MVP) ` `(Level-2) `_ ##

An Activity to show list of channels with currently on air event


#### Main Functionality ####

- Loads List of channels and Events from `API`
- Show OnAir Event according to current timezone on **Device**
- Uses **cache** to hold data after a successful response
- **cache** is used to persist events from at least one hour ahead of now

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

- By clicking a row, goes to `Details Activity` according to the clicking area of the row 

