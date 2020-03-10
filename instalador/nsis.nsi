# Nombre del instalador
Name "Instalador de Cliente Correo"

# The file to write
OutFile "Cliente Correo.exe"

# The default installation directory
InstallDir $PROGRAMFILES\ClienteCorreo

# Pedimos permisos para Windows 7
RequestExecutionLevel admin

# Pantallas que hay que mostrar del instalador

Page directory
Page instfiles

#Seccion principal
Section

  # Establecemos el directorio de salida al directorio de instalacion
  SetOutPath $INSTDIR 
  
  # Creamos el desinstalador
  writeUninstaller "$INSTDIR\uninstall.exe"
  
  # Ponemos ahi el archivo test.txt
  File /r out\artifacts\ClienteCorreo_jar
  File /r help
  File /r jasper
  File /r out\artifacts\ClienteCorreo_jar\javafx-sdk-13.0.1
  File /r out\artifacts\ClienteCorreo_jar\java-runtime
  

  
  
  createShortCut "$MPROGRAMS\miaplicaion.lnk" "$INSTDIR\java-runtime\bin\java --module-path $INSTDIR\javafx-sdk-13.0.1\lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED -jar $INSTDIR\ClienteCorreo.jar" 
  
  
  #Añadimos información para que salga en el menú de desinstalar de Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreo" \
                 "DisplayName" "ClienteCorreo"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreo" \
                 "Publisher" "Pablo - Desarrollo Interfaces"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreo" \
                 "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
  
# Fin de la seccion
SectionEnd

# seccion del desintalador
section "uninstall"
 
    # borramos el desintalador primero
    delete "$INSTDIR\uninstall.exe"
 
    # borramos el acceso directo del menu de inicio
    delete "$INSTDIR\test.txt"
	
	RmDir "$INSTDIR"
	
	#Borramos la entrada del registro
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreo"
 
# fin de la seccion del desinstalador
sectionEnd