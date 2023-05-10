# Examplecode for problematic redux function call

Steps to reproduce:
- start :jsBrowserRun
- open the developer console in the browser

You should see an error like this:
````
Uncaught TypeError: queryState.get_currentQuery_smfme0_k$ is not a function
    at Welcome$lambda$lambda (Welcome.kt:24:20)
    at useSelectorWithStoreAndSubscription (useSelector.js:29:1)
    at useSelector (useSelector.js:129:1)
    at Welcome$lambda (Welcome.kt:31:17)
    at eval (FC.kt:7:31)
    at createElementOrNull (Standard.kt:6:10)
    at eval (FC.kt:7:9)
    at renderWithHooks (react-dom.development.js:16305:1)
    at mountIndeterminateComponent (react-dom.development.js:20074:1)
    at beginWork (react-dom.development.js:21587:1)
Welcome$lambda$lambda @ Welcome
````