## Login _`(MVP) ` `(Level-3) `_ ##

An Activity to show list of channels with currently on air event


#### Main Functionality ####

- Show Image and Name of Loggedin user
- Fetches User settings from `AWS EC2` server after successful login
- Sends User Settings to `AWS EC2` before logout
- Show OnAir Event according to current timezone on **Device**
- Uses **cache** to hold data after a successful response
- **cache** is used to persist events from at least one hour ahead of now

#### Construction Elements / Dependencies ####

- Uses [Google SSO](https://developers.google.com/identity/sign-in/android/)
- Module to provide Dagger2 support 
- Component
- Interactor
- Presenter
- XML layout

##### Presenter #####

- Provides `Interface` and implementation to manipulate UI and functionality for the Activity.
- Uses Decoupled classes for Functionality and Ui
- Implemets `OnSyncSettingsListener`
- Implemets `LoginPresenter`

##### Interactor #####

- Provides `interface` implementation of Use-cases, i.e. `OnSyncSettingsListener`
- Updates the User Settings accross the application


#### Propogates Changes ####

- `Goodle SignIn Button`, initializes the Signin process
- `Logout`, initializes the Logout process

