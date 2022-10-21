git filter-branch --force --commit-filter '
                GIT_COMMITTER_NAME="tandt53";
                GIT_AUTHOR_NAME="tandt53";
                GIT_COMMITTER_EMAIL="dothetan.040490@gmail.com";
                GIT_AUTHOR_EMAIL="dothetan.040490@gmail.com";
                git commit-tree "$@";
        '  --tag-name-filter cat -- --all
