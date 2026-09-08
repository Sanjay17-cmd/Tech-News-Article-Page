# JavaScript Lab Work - Complete Study Guide

## Understanding 8 Core Features in Your Code

---

## 📋 **Feature 1: CONTENT SLIDER**

**What it does:** Lets users navigate through multiple articles (news items) using Previous/Next buttons.

### Where it's implemented:

#### **Step 1: Data Storage (Lines 1-11)**

```javascript
const articles = [
  [
    "AI in Everyday Devices",
    "AI helps phones, cameras and smart home devices.",
    "AI powers voice assistants, smart cameras and recommendations."
  ],
  [
    "New Web Design Trends",
    "Clean layouts and bright buttons are popular.",
    "Modern pages use responsive design, simple colors and clear sections."
  ],
  [
    "Gaming Hardware Updates",
    "New devices are faster and cooler.",
    "Gaming laptops and consoles now focus on speed, graphics and battery life."
  ]
];
```

**Explanation:**

- Array of 3 articles (each with 3 items: title, summary, details)
- Think of it like a deck of 3 cards stacked on top of each other

#### **Step 2: Track Current Position (Line 23)**

```javascript
let i = 0;  // i = current article index (0, 1, or 2)
```

#### **Step 3: Display Article Function (Lines 27-33)**

```javascript
function show() {
  if (!$("articleTitle")) return;
  let a = articles[i];  // Get current article
  $("articleTitle").textContent = a[0];    // Show title
  $("articleSummary").textContent = a[1];  // Show summary
  $("articleDetails").textContent = "Click Read More to see details.";
}
```

#### **Step 4: Navigation Buttons (Lines 64-72)**

```javascript
$("prevBtn")?.addEventListener("click", () => {
  i = (i + articles.length - 1) % articles.length;
  show();
});

$("nextBtn")?.addEventListener("click", () => {
  i = (i + 1) % articles.length;
  show();
});
```

**How it works:**

- **PREV button:** `i = (i + 3 - 1) % 3` → Goes backward through articles (loops to end)
- **NEXT button:** `i = (i + 1) % 3` → Goes forward through articles (loops to start)
- Example: If i=2, click NEXT → i becomes (2+1)%3 = 0 (wraps around)

#### **Step 5: Show Details (Lines 74-77)**

```javascript
$("readMoreBtn")?.addEventListener("click", () => {
  $("articleDetails").textContent = articles[i][2];  // Show full article
  alert("Now reading: " + articles[i][0]);
});
```

---

## 🔢 **Feature 2: RANDOM NUMBER GENERATION FOR REGISTRATION**

**What it does:** Creates a unique visitor ID for each new user.

### Where it's implemented:

#### **Lines 43-48: Create Random ID Function**

```javascript
function newId() {
  let n = Math.floor(Math.random() * 90000) + 10000;
  // Math.random() → generates 0 to 1 (like 0.534)
  // × 90000 → becomes 0 to 90000
  // Math.floor() → rounds down to whole number
  // + 10000 → adds 10000, so final range is 10000-99999
  
  setCookie("visitorId", n, 7);  // Save the ID to cookies
  return n;  // Return the ID
}
```

**Visual Example:**

```
Math.random()     = 0.456
× 90000           = 41040
Math.floor()      = 41040
+ 10000           = 51040 ✓ (Your unique ID!)
```

#### **Line 24: First Time Setup**

```javascript
let id = getCookie("visitorId") || newId();
// Check if ID exists in cookies
// If not, create new random ID
```

#### **Registration Form (Line 139)**

```javascript
alert("Registration complete! Your number is " + id);
// Shows the random visitor ID to user
```

---

## 🎨 **Feature 3: DYNAMIC CONTENT DISPLAY**

**What it does:** Updates page content without refreshing (changes happen live on the page).

### Where it's implemented:

#### **The `$()` Helper Function (Line 21)**

```javascript
const $ = id => document.getElementById(id);
// Shortcut to get HTML elements by ID
// Example: $("articleTitle") = get element with id="articleTitle"
```

#### **Dynamic Updates in Different Functions:**

**A) Show articles dynamically:**

```javascript
$("articleTitle").textContent = a[0];    // Changes title text
$("articleSummary").textContent = a[1];  // Changes summary text
```

**B) Show welcome message:**

```javascript
$("welcomeText").textContent = n
  ? `Welcome back, ${decodeURIComponent(n)}! ID: ${id}`
  : "Welcome! Your info will appear below.";
// If name exists → show personalized message
// Otherwise → show default message
```

**C) Live form preview:**

```javascript
function preview() {
  if ($("previewText"))
    $("previewText").textContent =
      $("name").value && $("topic").value
        ? `Hello ${$("name").value}, you like ${$("topic").value}.`
        : "Type name and choose topic.";
}
// Updates text as user types in form
```

**D) Show contact result:**

```javascript
$("contactResult").textContent = `Hi ${n}! Welcome to contact page.`;
// Updates page with form response
```

**E) Hover effect (scale animation):**

```javascript
$("slideCard")?.addEventListener("mouseover", () => {
  $("slideCard").style.transform = "scale(1.02)";  // Makes card 2% bigger
});
```

---

## 🍪 **Feature 4: COOKIES (Storing User Data)**

**What it does:** Saves user information (name, email, ID) so it persists when they return.

### Where it's implemented:

#### **Set Cookie Function (Lines 35-38)**

```javascript
function setCookie(n, v, d) {
  // n = name of cookie (like "visitorName")
  // v = value to store (like "John")
  // d = days to keep cookie (like 7)
  
  let e = new Date(Date.now() + d * 864e5).toUTCString();
  // 864e5 = 86400000 milliseconds = 1 day
  // d * 864e5 = number of days converted to milliseconds
  // Calculate expiration date
  
  document.cookie = `${n}=${encodeURIComponent(v)};expires=${e};path=/`;
  // encodeURIComponent() = safely encode special characters
}
```

**Example:**

```
setCookie("visitorName", "Alice", 7)
Creates: visitorName=Alice; expires=10-Jul-2026; path=/
```

#### **Get Cookie Function (Lines 40-46)**

```javascript
function getCookie(n) {
  return (
    document.cookie
      .split(";")           // Break into individual cookies
      .map(c => c.trim())   // Remove extra spaces
      .find(c => c.startsWith(n + "="))  // Find the one we want
      ?.split("=")          // Split name from value
      .slice(1)             // Get only the value part
      .join("=") || ""      // Join it back, or return empty string
  );
}
```

#### **Where Cookies Are Used:**

**Line 24:** Create or retrieve visitor ID

```javascript
let id = getCookie("visitorId") || newId();
```

**Lines 103-117:** Save registration data

```javascript
setCookie("visitorName", n, 7);
setCookie("visitorEmail", em, 7);
setCookie("favoriteTopic", t, 7);
setCookie("registered", "yes", 7);
```

**Lines 78-89:** Display saved cookies

```javascript
function info() {
  let n = getCookie("visitorName");
  let e = getCookie("visitorEmail");
  let t = getCookie("favoriteTopic");
  
  $("welcomeText").textContent = n
    ? `Welcome back, ${decodeURIComponent(n)}! ID: ${id}`
    : "Welcome! Your info will appear below.";
}
```

**Lines 94-99:** Clear all cookies

```javascript
$("clearCookiesBtn")?.addEventListener("click", () => {
  if (confirm("Clear saved cookies?"))
    ["visitorId", "visitorName", "visitorEmail", "favoriteTopic", "registered"]
      .forEach(x => setCookie(x, "", -1));  // Sets expiration to past
  info();  // Refresh display
});
```

---

## 🖱️ **Feature 5: DOM MANIPULATION & DATA PROCESSING**

**What it does:** Changes HTML elements and processes/transforms data.

### Where it's implemented:

#### **A) Get Element Values (Reading Data)**

```javascript
let n = $("name").value.trim();          // Get name, remove spaces
let em = $("email").value.trim();        // Get email
let p = $("password").value;             // Get password
let cp = $("confirmPassword").value;     // Get confirm password
```

#### **B) Change Element Content (Writing Data)**

```javascript
$("articleTitle").textContent = a[0];         // Change text content
$("slideCard").style.transform = "scale(1.02)";  // Change CSS style
```

#### **C) Check if Element Exists**

```javascript
if (!$("articleTitle")) return;  // Skip if element doesn't exist
if ($("welcomeText")) { ... }    // Only change if exists
```

#### **D) Encode/Decode Data for Safety**

```javascript
encodeURIComponent(v)        // Convert: "John Doe" → "John%20Doe"
decodeURIComponent(n)        // Convert back: "John%20Doe" → "John Doe"
// Needed because some characters break cookies
```

#### **E) Transform Multiple Values**

```javascript
$("cookieInfo").textContent = `Saved ID: ${id}${
  e ? " | Email: " + decodeURIComponent(e) : ""
}${t ? " | Topic: " + decodeURIComponent(t) : ""}`;
// Builds string only with data that exists
```

#### **F) Check Multiple Conditions**

```javascript
if (n.length < 3) return alert("Name must have at least 3 letters.");
if (!em.includes("@") || !em.includes(".")) return alert("Enter valid email.");
if (p.length < 6) return alert("Password must be at least 6 characters.");
if (p !== cp) return alert("Passwords do not match.");
if (!t) return alert("Choose a favorite topic.");
if (!$("terms").checked) return alert("Please agree to updates.");
```

---

## ⚡ **Feature 6: EVENTS CONCEPT**

**What it does:** Makes page react when user clicks, types, hovers, or submits forms.

### Where it's implemented:

#### **A) CLICK Events**

```javascript
// Previous button click
$("prevBtn")?.addEventListener("click", () => {
  i = (i + articles.length - 1) % articles.length;
  show();
});

// Next button click
$("nextBtn")?.addEventListener("click", () => {
  i = (i + 1) % articles.length;
  show();
});

// Read More button click
$("readMoreBtn")?.addEventListener("click", () => {
  $("articleDetails").textContent = articles[i][2];
  alert("Now reading: " + articles[i][0]);
});

// Clear cookies button click
$("clearCookiesBtn")?.addEventListener("click", () => {
  if (confirm("Clear saved cookies?")) { ... }
  info();
});

// Contact form button click
$("askNameBtn")?.addEventListener("click", () => {
  let n = prompt("What is your name?");
  if (n) $("contactResult").textContent = `Hi ${n}! Welcome to contact page.`;
});
```

#### **B) MOUSEOVER/MOUSEOUT Events (Hover Effects)**

```javascript
$("slideCard")?.addEventListener("mouseover", () => {
  $("slideCard").style.transform = "scale(1.02)";  // Makes bigger on hover
});

$("slideCard")?.addEventListener("mouseout", () => {
  $("slideCard").style.transform = "scale(1)";  // Back to normal
});
```

#### **C) INPUT Event (Real-time Typing)**

```javascript
$("regForm")?.addEventListener("input", preview);
// Updates preview as user types name or topic
```

#### **D) CHANGE Event**

```javascript
$("topic")?.addEventListener("change", preview);
// Updates preview when user selects different topic
```

#### **E) SUBMIT Event (Form Submission)**

```javascript
$("contactForm")?.addEventListener("submit", e => {
  e.preventDefault();  // Stop page from refreshing
  let s = $("subject").value.trim();
  let m = $("message").value.trim();
  
  if (!s || !m) return alert("Enter subject and message.");
  
  if (confirm("Send this message?"))
    $("contactResult").textContent = "Message sent! Subject: " + s;
});

$("regForm")?.addEventListener("submit", e => {
  e.preventDefault();  // Stop page from refreshing
  // ... validate and process registration
});
```

#### **The `?. ` Operator (Optional Chaining)**

```javascript
$("prevBtn")?.addEventListener(...)
// Means: If prevBtn exists, add listener. If not, do nothing safely.
// Prevents errors if element doesn't exist on current page
```

---

## 🎯 **Feature 7: ALERT, PROMPT, CONFIRM METHODS**

**What it does:** Shows pop-up messages to interact with the user.

### Where it's implemented:

#### **A) ALERT - Shows a message with OK button**

```javascript
// Display article on read more click
alert("Now reading: " + articles[i][0]);
// Shows: "Now reading: AI in Everyday Devices" → [OK]

// Form validation errors
alert("Enter subject and message.");
// Shows: "Enter subject and message." → [OK]

alert("Name must have at least 3 letters.");
alert("Enter valid email.");
alert("Password must be at least 6 characters.");
alert("Passwords do not match.");
alert("Choose a favorite topic.");
alert("Please agree to updates.");

// Success message
alert("Registration complete! Your number is " + id);
// Shows: "Registration complete! Your number is 45821" → [OK]
```

#### **B) PROMPT - Asks user for input**

```javascript
$("askNameBtn")?.addEventListener("click", () => {
  let n = prompt("What is your name?");
  // Shows: "What is your name?" → [Cancel] [OK]
  // User types their name, clicks OK
  // Variable n stores what they typed
  
  if (n) $("contactResult").textContent = `Hi ${n}! Welcome to contact page.`;
  // If they entered something (not clicked Cancel), show message
});
```

#### **C) CONFIRM - Yes/No question**

```javascript
// Clear cookies confirmation
$("clearCookiesBtn")?.addEventListener("click", () => {
  if (confirm("Clear saved cookies?")) {
    // Shows: "Clear saved cookies?" → [Cancel] [OK]
    // If user clicks OK:
    ["visitorId", "visitorName", "visitorEmail", "favoriteTopic", "registered"]
      .forEach(x => setCookie(x, "", -1));
  }
  // If user clicks Cancel, nothing happens
  info();
});

// Send message confirmation
if (confirm("Send this message?")) {
  // Shows: "Send this message?" → [Cancel] [OK]
  // Only process if they click OK
  $("contactResult").textContent = "Message sent! Subject: " + s;
}
```

---

## ✅ **Feature 8: FORM VALIDATION**

**What it does:** Checks user input before accepting it (makes sure data is correct).

### Where it's implemented:

#### **Contact Form Validation (Lines 119-127)**

```javascript
$("contactForm")?.addEventListener("submit", e => {
  e.preventDefault();  // Stop default form action
  let s = $("subject").value.trim();    // Get and clean subject
  let m = $("message").value.trim();    // Get and clean message
  
  // Check 1: Both fields must have text
  if (!s || !m) return alert("Enter subject and message.");
  
  // If valid, ask for confirmation
  if (confirm("Send this message?"))
    $("contactResult").textContent = "Message sent! Subject: " + s;
});
```

#### **Registration Form Validation (Lines 128-153)**

```javascript
$("regForm")?.addEventListener("submit", e => {
  e.preventDefault();  // Stop page from refreshing
  
  let n = $("name").value.trim();
  let em = $("email").value.trim();
  let p = $("password").value;
  let cp = $("confirmPassword").value;
  let t = $("topic").value;

  // Validation Check 1: Name length
  if (n.length < 3) return alert("Name must have at least 3 letters.");
  // "Jo" → ERROR (too short)
  // "John" → OK (4 letters)

  // Validation Check 2: Email format
  if (!em.includes("@") || !em.includes(".")) 
    return alert("Enter valid email.");
  // "john@gmail.com" → OK (has @ and .)
  // "john.gmail.com" → ERROR (no @)
  // "john@gmail" → ERROR (no .)

  // Validation Check 3: Password length
  if (p.length < 6) return alert("Password must be at least 6 characters.");
  // "pass" → ERROR (4 characters)
  // "password123" → OK (11 characters)

  // Validation Check 4: Passwords match
  if (p !== cp) return alert("Passwords do not match.");
  // "password123" vs "password123" → OK
  // "password123" vs "password1234" → ERROR

  // Validation Check 5: Topic selected
  if (!t) return alert("Choose a favorite topic.");
  // If dropdown is empty → ERROR
  // If something is selected → OK

  // Validation Check 6: Terms accepted
  if (!$("terms").checked) return alert("Please agree to updates.");
  // If checkbox is unchecked → ERROR
  // If checkbox is checked → OK

  // If ALL validations pass, save cookies
  setCookie("visitorName", n, 7);
  setCookie("visitorEmail", em, 7);
  setCookie("favoriteTopic", t, 7);
  setCookie("registered", "yes", 7);
  
  alert("Registration complete! Your number is " + id);
  info();  // Update page with new info
});
```

#### **Form Preview Validation (Lines 108-114)**

```javascript
function preview() {
  if ($("previewText"))
    $("previewText").textContent =
      $("name").value && $("topic").value  // Both must have values
        ? `Hello ${$("name").value}, you like ${$("topic").value}.`
        : "Type name and choose topic.";
}
// Checks if both fields exist before showing preview
// If either is empty → shows instruction text
// If both filled → shows personalized message
```

---

## 📊 **QUICK REFERENCE TABLE**


| Feature              | What It Does          | Key Functions/Methods                                   |
| -------------------- | --------------------- | ------------------------------------------------------- |
| **Slider**           | Navigate articles     | `show()`, `addEventListener("click")` on prev/next      |
| **Random Number**    | Create unique ID      | `Math.random()`, `Math.floor()`, `newId()`              |
| **Dynamic Display**  | Update page content   | `.textContent`, `.style`, `$()` selector                |
| **Cookies**          | Store user data       | `setCookie()`, `getCookie()`, `document.cookie`         |
| **DOM Manipulation** | Modify HTML elements  | `.value`, `.textContent`, `.style`, `.checked`          |
| **Events**           | React to user actions | `.addEventListener()` - click, mouseover, submit, input |
| **Alerts**           | Show pop-ups          | `alert()`, `prompt()`, `confirm()`                      |
| **Validation**       | Check data is correct | `if()` statements, `.length`, `.includes()`, `.checked` |

---

## 🔍 **HOW EVERYTHING WORKS TOGETHER**

**User Journey:**

1. **Page Loads** → `show()` and `info()` run at end of script
2. **User clicks NEXT** → Event triggers → `i` changes → `show()` updates article
3. **User hovers over article** → `mouseover` event → card scales up
4. **User clicks Read More** → Shows details + `alert()` pop-up
5. **User fills registration form** → `input` event triggers → `preview()` updates
6. **User submits form** → `submit` event → `validate()` checks all fields
7. **If valid** → `confirm()` asks confirmation → `setCookie()` saves data
8. **Next visit** → `getCookie()` finds saved data → `info()` shows welcome back message

---

## 💡 **KEY CONCEPTS TO REMEMBER**

- **Arrays** store multiple items: `articles[0]`, `articles[1]`, `articles[2]`
- **`i` variable** tracks position in array (0, 1, or 2)
- **`%` operator** makes numbers loop (modulo operator)
- **Functions** do specific tasks and can be reused
- **Events** make page interactive (click, hover, submit, etc.)
- **DOM** = the HTML structure, we change it with JavaScript
- **Cookies** = browser's memory, persist across page loads
- **Validation** = check data before using it
