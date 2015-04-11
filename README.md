# wassup
INFSI351

## Add project's android dependencies
Following the instruction here: Adding libraries with resources (http://developer.android.com/tools/support-library/setup.html)

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
![Mes Evenements](https://github.com/tdhman/wassup/blob/master/Screenshots/evenement.jpg "Mes Evenements")
![Evenement](https://github.com/tdhman/wassup/blob/master/Screenshots/mes_evenements.jpg "Evenement")
![Mes Invitations](https://github.com/tdhman/wassup/blob/master/Screenshots/mes_invitation.jpg "Mes Invitations")
![Invitation](https://github.com/tdhman/wassup/blob/master/Screenshots/invitation.jpg "Invitation")
