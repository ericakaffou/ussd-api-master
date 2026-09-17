Write-Host "=========================================="
Write-Host "  Clonage du projet "
Write-Host "=========================================="

$HOME_PATH = Get-Location
$REPO_URL = "https://github.com/ericakaffou/ussd-api-master.git"

$DEST_REP = "ussd-app1"
$DEST_PATH = "C:\Users\Eric\Desktop\OneDrive\fORMATIONS\DevOps_Projets\USSD-deploy(Gitlab-Jenkins-dockerRegistry-Ansible)\$DEST_REP\$DEST_REP"
#$COMMIT_MSG = "scriptCommit-" + (Get-Date -Format "dddd_MM-dd-yyyy_HH:mm K")
$COMMIT_MSG = "scriptCommit-$(Get-Date -Format 'MM-dd-yyyy_HH:mm K')"

Write-Host ""
Write-Host "DEST_PATH :"
Write-Host $DEST_PATH

Write-Host ""
Write-Host "DEST_REP :"
Write-Host $DEST_REP

Write-Host ""

# ==========================================
# Suppression d'un ancien clone temporaire
# ==========================================

if (Test-Path $DEST_REP"_tmp") {

    Write-Host "Suppression de l'ancien clone..."

    Remove-Item $DEST_REP"_tmp" -Recurse -Force
}

# ==========================================
# Verification de l'argument
# ==========================================

# if ($args.Count -eq 0) {
#
#     Write-Host ""
#     Write-Host "ERREUR : Vous devez fournir un repertoire de destination."
#     Write-Host ""
#     Write-Host "Exemple :"
#     Write-Host "  .\script.ps1 `"C:\Users\Desire\Documents\projets`""
#     Write-Host ""
#     pause
#     exit 1
# }

# ==========================================
# Clonage du depot
# ==========================================

Write-Host ""
Write-Host "Clonage du depot..."

git clone $REPO_URL $DEST_REP"_tmp"

if ($LASTEXITCODE -ne 0) {

    Write-Host ""
    Write-Host "ERREUR : Le clonage a echoue."

    pause
    exit 1
}

Write-Host ""
Write-Host "Clonage termine avec succes !"

# ==========================================
# Creation du repertoire destination
# ==========================================

if (Test-Path $DEST_PATH) {

    Write-Host ""
    Write-Host "Suppression de l'ancienne copie..."

    Remove-Item $DEST_PATH -Recurse -Force
}

if (-not (Test-Path $DEST_PATH)) {

    New-Item $DEST_PATH -ItemType Directory | Out-Null
}

# ==========================================
# Copie SANS le dossier .git
# ==========================================

Write-Host ""
Write-Host "Copie du projet sans le depot Git..."

robocopy $DEST_REP"_tmp" $DEST_PATH /E

# enlève les attributs caché/système/lecture seule

attrib -H -S -R "$DEST_PATH\.git" /S /D
attrib -H -S -R "$DEST_PATH\.github" /S /D

Write-Host ""
Write-Host "Suppression du depot Git  et du dossier .github..."

Remove-Item "$DEST_PATH\.git" -Recurse -Force

Remove-Item "$DEST_PATH\.github" -Recurse -Force

if (Test-Path "$DEST_PATH\.git") {

    Write-Host "Suppression du dossier .git..."

    Remove-Item "$DEST_PATH\.git" -Recurse -Force
}

if (Test-Path "$DEST_PATH\.github") {

    Write-Host "Suppression du dossier .github..."

    Remove-Item "$DEST_PATH\.github" -Recurse -Force
}
# Robocopy retourne plusieurs codes consideres comme normaux.
if ($LASTEXITCODE -ge 8) {

    Write-Host ""
    Write-Host "ERREUR : La copie a echoue."

    pause
    exit 1
}

Write-Host ""
Write-Host "Copie terminee."

# ==========================================
# Suppression du clone temporaire
# ==========================================

Write-Host ""
Write-Host "Suppression du clone temporaire..."


Remove-Item $DEST_REP"_tmp" -Recurse -Force

Write-Host ""
Write-Host "Deplacement dans :"
Write-Host $DEST_PATH

Set-Location $DEST_PATH

# ==========================================
# Push vers la repository local gitlab 
# ==========================================
Write-Host "=========================================="
Write-Host "  Push vers la repository local gitlab "
Write-Host "=========================================="

Write-Host ""
Write-Host "Execution de git add . ..."

git add .

Write-Host ""
Write-Host "Execution de git commit -m $COMMIT_MSG"

git commit -m "$COMMIT_MSG"

Write-Host ""
Write-Host "Execution de git push origin main"

git push origin main

if ($LASTEXITCODE -ne 0) {

    Write-Host ""
    Write-Host "ERREUR : Impossible d'acceder au repertoire destination."

    pause
    exit 1
}

Write-Host ""
Write-Host "=========================================="
Write-Host "  Operation terminee avec succes !"
Write-Host "=========================================="

Set-Location $HOME_PATH

exit 0