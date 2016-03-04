Fountain MongoDB Config
=======================


1. Install MongoDB - make sure that you have the correct version with respect to Windows 2008 see: 

2. Create a service to run MongoDB by running mongos.exe in the mongo bin dir with no parameters. 

3. Using a client or the mongo shell add the required users to the admin and appropriate databases:

	* These are: 

	db.addUser( { user: "FountainExcel",
              pwd: "FountainExcel",
              roles: [ "readWriteAnyDatabase" ] } )

   	db.addUser( { user: "fountain",
              pwd: "fountain",
              roles: [ "userAdminAnyDatabase" ] } )

4. Modify the Mongo config file, mongod.cfg, that lives in the root of the mongo working directory. Add the following setting:
`auth=true` after the logpath and dbpath entries.

5. Restart the mongodb service. 

