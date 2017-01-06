# AndroidBinderDemo
Sample code for how to use Android Binder to implement IPC functions.

# Quick overview
There are several branches show the different use with binder, the differences
are about **"is in same app? is in same process?"**.

## master branch
Use the binder in one app, but access Service that run in another process.

## DifferentApp branch
Use the binder to communicate with other app, thus access Service that run in another process.
