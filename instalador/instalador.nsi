Name "Mensajeria "

OutFile "ClienteCorreoJB.exe"


InstallDir $PROGRAMFILES\ClienteCorreoJB

# Pedimos permisos para Windows. 

RequestExecutionLevel admin

# Pantallas que hay que mostrar del instalador

Page directory
Page instfiles

#idioma
!include "MUI.nsh"
!insertmacro MUI_LANGUAGE "Spanish"


!include "MUI2.nsh"

#!define MUI_ICON "C:\Users\Jesús Brandy\Desktop\icono.ico"


#Seccion principal
Section




# directorio de instalacion
SetOutPath $INSTDIR\files

# Creamos el desinstalador
writeUninstaller "$INSTDIR\uninstall.exe"


File /r "..\out\artifacts\ClienteCorreo_jar\*" 
SetOutPath $INSTDIR\files\help
File  "..\help\articles.zip" 

SetOutPath $INSTDIR\files
File /r "..\jasper" 
SetOutPath $INSTDIR\files\jasper
File /r "..\jasper\img" 


SetOutPath $INSTDIR\files\javafx 
File /r "C:\Users\Jesús Brandy\Desktop\javafx-sdk-13.0.1\*"
#  JRE

SetOutPath $INSTDIR\files\java-runtime 
File /r "C:\Java\jdk-13\bin\java-runtime\*"

WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreoJB\
             "DisplayName" "ClienteCorreoJBrandy"
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreoJB\
             "Publisher" "J.Brandy - Studio"
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreoJB\
             "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
SetOutPath $INSTDIR


createShortCut "$SMPROGRAMS\Desinstalar.lnk" "$INSTDIR\uninstall.exe"
createShortCut "$DESKTOP\Desinstalar.lnk" "$INSTDIR\uninstall.exe"


SetOutPath $INSTDIR\files
CreateShortCut \
  `$DESKTOP\ClienteCorreo.lnk` \ `$INSTDIR\files\java-runtime\bin\java.exe` \ `--module-path "$INSTDIR\files\javafx-sdk-13.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web,javafx.base --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED -jar "$INSTDIR\files\ClienteCorreo.jar"` \
  
SetOutPath $INSTDIR

SectionEnd


section "uninstall"


delete "$INSTDIR\uninstall.exe"


RmDir /r "$INSTDIR\files"
RmDir "$INSTDIR"



DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ClienteCorreo"


delete "$SMPROGRAMS\Desinstalar.lnk"
delete "$DESKTOP\Desinstalar.lnk"
delete "$DESKTOP\ClienteCorreo.lnk"


sectionEnd