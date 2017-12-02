# SELF

## Two Primary Resource Division

1. UI
2. Non-UI

### UI

1. Styles (for color, custom button design and theme)
   located at: res/values/style.xml
2. Strings (for UI messages, placeholders, label names and Toast messages)
    located at: res/values/strings.xml
3. Images (Icons, Custom Usage & Pictures)
    located at: res/drawable/
4. Colors (Custom name to hex values of Colors can be stroed here)
    located at: res/values/colors.xml
5. Layouts (Where UI XML files will go describing Screen Layout)
    located at: res/layout/

### Non-UI

1. Activity (Java class mapping 1-1 corressponding to Layouts from driving actions from screen)
    located at: app/Java/com/project/self/Activity/
2. Helper (Java Classes which will work on threads to process and transport data from UI to other services)
    located at: app/Java/com/project/self/Helper/
3. DataClass (Java Classes for Creating Structued Data Holder- Getter and Setter types e.g. Consts)
    located at: app/Java/com/project/self/DataClass/
