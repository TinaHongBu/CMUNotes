# Git Notes

* Gitignore
If you want to ignore things you already commited previously, you need to clear the cache.

NOTE : First commit your current changes, or you will lose them.

Then run the following commands from the top folder of your git repo:
```
git rm -r --cached .
git add .
git commit -m "fixed untracked files"
```
