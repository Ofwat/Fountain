;Script to populate the suth dialog when running the functional tests!
;webdriver has no way of doing this natively!
If $CMDLINE[0] = 2 Then
	;Enter a loop to wait for any dialogs that match the auth screens. 
	Local $iMsg = 0
	Beep(500, 1000)
	While 1
		$aMsg = GUIGetMsg(1)
		$title = WinGetTitle($aMsg[1])
		ConsoleWrite($title) 
		If ($title == "Authentication Required") Or ($title == "Windows Security") Then
		   $UN=WinGetText($title,"User Name:")  
		   ControlSend($title,"",$UN,$CmdLine[1]);Sets Username  
		   $PWD=WinGetText($title,"Password:")  
		   ControlSend($title,"",$UN,"{TAB 1}")  
		   ControlSend($title,"",$PWD,$CmdLine[2]);Sets PWD  
		   ;sleep here!
		   Sleep(1000)
		   ControlSend($title,"",$PWD,"{ENTER}")  			
		EndIf
	WEnd
	;Run the loop and kill this process after 10minutes. 
Else
   ConsoleWrite("Please supply 2 params; username & password.")
   Exit 1
EndIf









