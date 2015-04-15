# wassup
INFSI351

## Add project's android dependencies
Following the instruction here: Adding libraries with resources (http://developer.android.com/tools/support-library/setup.html)

Install Google Play Service using Android SDK Manager. Then go to android-sdk folder and copy lib folder to Eclipse workspace: extras/google/..../google-play-service-lib
Import this lib folder into Eclipse, then in Wassup project's properties, select Android tab and add this lib project into Wassup. (Similarly to add support library)

After adding Google Play Service, to activate Googla Maps to work with your Eclipse, go to https://console.developers.google.com to create new project called "Wassup". Then go to APIs tab to enable Google Maps Android API v2. Next, go to Credentials tab, create new Public API key by coping the following key to Android Application Key: ** AA:F0:2D:04:54:59:B8:5C:FE:FF:9F:20:4A:33:B8:A7:C6:C0:B9:A8;enst.infsi351.wassup**
Click OK and then you will have a new API Key for Android.
Copy this key and go back to your Eclipse, edit AndroidManifest.xml and change the following key to your API key:
            android:name="com.google.android.geo.API_KEY"
            android:value="<Your API Key>"

Then, modify the library project's .properties: change **target=android-19** to **target=android-21**

## TODO: Implementation
We can use DrawerLayout to define the leftmenu and main content region, and for different tasks, we can redefine their layout using "Fragment" (not "Activity"). 

The MainActivity class have already defined the leftmenu as ListView and main content as FrameLayout. For different tasks, we need to re-implement their Fragment class and layout. The Fragments class and layout for each task have been added in the project and each team member only need to modify files corresponding to their assigned task.

Below is the list of tasks with their corresponding class and layout:
- *Acceuil*: AcceuilFragment + fragment_acceuil
- *Mes Evenements*: MesEvenementsFragment + activity_screen_slide + fragment_screen_slide_page
- *Mes Invitations*: MesInvitationsFragment + fragment_mes_invitation
- *Mes Notification*: MesNotificationsFragment + fragment_mes_notifications
- *Mon Compte*: MonCompteFragment + fragment_mon_compte
- *Invitation*: InvitationFragment + fragment_invitation

However, for Advanced Search task, we can use Activity to implement it ( because it has multilple screen steps but i am not sure) so you should decide it by yourself! In MainActivity, the Advanced Search is in ActionBar of rightmenu and i have added "TODO" comment to mark the section of where you can put your Activity/Fragment call. Beside, for the sliding screen effect in Advanced Search, I suggest to use ViewPager as I did for Mes Evenements.

## Finished tasks
- *Mes Evenements*: MesEvenementsFragment + fragment_mes_evenements
- *Evenement*: EvenementFragment + fragment_evenement
- *Mes Invitations*: MesInvitationsFragment + fragment_mes_invitations + fragment_list_item
- *Invitation*: InvitationFragment + fragment_invitation

## Screenshots
![Splash Screen](https://github.com/tdhman/wassup/blob/master/Screenshots/splash_screen.jpg "Splash Screen")
![Acceuil](https://github.com/tdhman/wassup/blob/master/Screenshots/acceuil.jpg "Acceuil")
![Mes Evenements](https://github.com/tdhman/wassup/blob/master/Screenshots/evenement.jpg "Mes Evenements")
![Evenement](https://github.com/tdhman/wassup/blob/master/Screenshots/mes_evenements.jpg "Evenement")
![Mes Invitations](https://github.com/tdhman/wassup/blob/master/Screenshots/mes_invitations.jpg "Mes Invitations")
![Invitation](https://github.com/tdhman/wassup/blob/master/Screenshots/invitation.jpg "Invitation")
![Mon Compte](https://github.com/tdhman/wassup/blob/master/Screenshots/mon_compte.jpg "Mon Compte")
