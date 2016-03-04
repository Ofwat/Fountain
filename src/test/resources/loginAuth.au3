;Script to populate the suth dialog when running the functional tests!
;webdriver has no way of doing this natively!
If $CMDLINE[0] = 2 Then
   AutoItSetOption("WinTitleMatchMode","2")  
   $hWnd = WinWaitActive("[REGEXPTITLE:(Authentication Required|Windows Security)]")   
   $title = WinGetTitle($hWnd) ; retrives whole window title   
   $UN=WinGetText($title,"User Name:")  
   ControlSend($title,"",$UN,$CmdLine[1]);Sets Username  
   $PWD=WinGetText($title,"Password:")  
   ControlSend($title,"",$UN,"{TAB 1}")  
   ControlSend($title,"",$PWD,$CmdLine[2]);Sets PWD  
   ;sleep here!
   Sleep(1000)
   ControlSend($title,"",$PWD,"{ENTER}")  
Else
   ConsoleWrite("Please supply 2 params; username & password.")
   Exit 1
EndIf









