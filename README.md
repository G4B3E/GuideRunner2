# GuideRunner

GuideRunner nevő projekt mobil alkalmazása.
Készítette: Deli Gábor

https://github.com/paltsm/guiderunner

# Leírás:

Ez a dokumentáció egy a Java nyelven írott, Android Studióban kíszett mobil alkalmazás dokumentálását hivatott bemutatni.

# A projekt felépülése: 
  * Projekt sablonja (template) empty activity-re épül rá.
  * Target API : 31 ( A megcélzott API-szint azt jelzi, hogy az alkalmazás hogyan fut a	eltérő Android-verziókon.)
  * CompileSdk: 33 ( Megadja azt az API-szintet, amelyre a projektet le kell fordítani.)
  * MinSdk: 26
  * TargetSdk: 33
  
## Implementations:
    ...
    ..
     'androidx.gridlayout:gridlayout:1.0.0'
     'androidx.appcompat:appcompat:1.6.1'
     'com.google.android.material:material:1.8.0'
     'androidx.constraintlayout:constraintlayout:2.1.4'
     'com.airbnb.android:lottie:4.1.0'
     'androidx.cardview:cardview:1.0.0'
     'androidx.cardview:cardview:1.0.0'
     'com.google.code.gson:gson:2.10'
    ..
    ...
    
## Activity-k és XML-ek:
  * SplashScreen/activity_splash_screen
  * OpenScreenMenu/activity_open_screen_menu
  * SignUp/activity_sign_up
  * LogIn/activity_log_in
  * BottomNav(Az ehhez tartozó fragmentek)/activity_bottom_nav
    * NewsFragment/fragment_news
    * SpeedRunsFragment/fragment_speed_runs
    * GameLibraryFragment/fragment_game_library
    * ProfileFragment/fragment_profile
  * SubmitSpeedRun/activity_submit_speed_run
  * MyProfileData/activity_my_profile_data
  * AboutUs/activity_about_us
  * DeleteAccountConfirmation/activity_delete_account_confirmation
  * LogOutConfirmation/activity_logout_confirmation
  * RefreshScreen/activity_refresh_page
  * A "GameLiberyFragement"- ben található jáékok activity-jei:
      * CupHead/activity_cup_head
      * Hades/activity_hades
      * HalfLife/activity_half_life
      * HollowKnight/activity_hollow_knight
      * JumpKing/activity_jump_king
      * Kotor/activity_kotor
      * KotorII/activity_kotor_ii
      * LegendOfZeldBOTW/activity_legends_of_zelda_botw
      * Minecraft/activity_minecraft
      * ResidentEvilIIRE/activity_residenteviliire
      * Undertale/activity_undertale
  
  
## Osztályok:
  * LocalHelper
  * Loginhelper
  * LogOutHelper
  * News
  * NewsLisHelper
  * Records
  * RequestHandler
  * Response 
  * TokenHelper
  * Users
  
## Activity-k/Osztályok/Fragmentek leírása:


| Fájlok | Típus | Leírás |
| ------------- | ------------- | ------------- |
| SplashScreen  | A mező nem maradhat üresen |  ------------- |
| IsString  | A mezőnek stringnek kell lennie("") | ------------- |
| IsUrl  | A mezőnek url-nek kell lennie | ------------- |
| MinLength  | A mező minimum karaktertartalma  | ------------- |
| MaxLength  | A mező maximum karaktertartalma  | ------------- |

  
  


