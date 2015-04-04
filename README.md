# wassup
INFSI351

## Add project's android dependencies
Following the instruction here: Adding libraries with resources (http://developer.android.com/tools/support-library/setup.html)

Then, modify the library project's .properties: change **target=android-19** to **target=android-21**

## TODO: Implementation
We can use DrawerLayout to define the leftmenu and main content region, and for different tasks, we can redefine their layout using "Fragment" (not "Activity"). 

The MainActivity class have already defined the leftmenu as ListView and main content as FrameLayout. For different tasks, we need to re-implement their Fragment class and layout. The Fragments class and layout for each task have been added in the project and each team member only need to modify their assigned task.

Below is the list of tasks with their corresponding class and layout:
- *Acceuil*: AcceuilFragment + fragment_acceuil
- *Mes Evenements*: MesEvenementsFragment + fragment_mes_evenements
- *Mes Invitations*: MesInvitationsFragment + fragment_mes_invitation
- *Mes Notification*: MesNotificationsFragment + fragment_mes_notifications
- *Mon Compte*: MonCompteFragment + fragment_mon_compte
