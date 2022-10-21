#!/bin/sh

# Credits: http://stackoverflow.com/a/750191

#git filter-branch -f --env-filter "
#    GIT_AUTHOR_NAME='tandt53'
#    GIT_AUTHOR_EMAIL='dothetan.040490@gmail.com'
#    GIT_COMMITTER_NAME='tandt53'
#    GIT_COMMITTER_EMAIL='dothetan.040490@gmail.com'
#  " HEAD

#git filter-branch --env-filter '
#OLD_EMAIL="v.tandt1@vinid.net"
#CORRECT_NAME="tandt53"
#CORRECT_EMAIL="dothetan.040490@gmail.com"
#if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
#then
#    export GIT_COMMITTER_NAME="$CORRECT_NAME"
#    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
#fi
#if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
#then
#    export GIT_AUTHOR_NAME="$CORRECT_NAME"
#    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
#fi
#' --tag-name-filter cat -- --branches --tags -f

git filter-branch --force --commit-filter '
        OLD_EMAIL="correct_email"
        CORRECT_NAME="tandt53"
        CORRECT_EMAIL="dothetan.040490@gmail.com"
        if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ];
        then
                GIT_COMMITTER_NAME="$CORRECT_NAME";
                GIT_AUTHOR_NAME="#CORRECT_NAME";
                GIT_COMMITTER_EMAIL="$CORRECT_EMAIL";
                GIT_AUTHOR_EMAIL="$CORRECT_EMAIL";
                git commit-tree "$@";
        else
                git commit-tree "$@";
        fi' HEAD