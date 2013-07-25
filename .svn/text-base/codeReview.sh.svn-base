yesterday=$(perl -e 'use POSIX;print strftime "%Y-%m-%d",localtime time-86400;')
tomorrow=$(perl -e 'use POSIX;print strftime "%Y-%m-%d",localtime time+86400;')

echo "[INFO]code changing between $yesterday and $tomorrow"
svn diff -r"{$yesterday}":"{$tomorrow}" > codeReview.diff