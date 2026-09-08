const articles = [
  [
    "AI in Everyday Devices",
    "AI helps phones, cameras and smart home devices."
  ],
  [
    "New Web Design Trends",
    "Clean layouts and bright buttons are popular."
  ],
  [
    "Gaming Hardware Updates",
    "New devices are faster and cooler."
  ]
];

const pageTips = [
  "Cookies let the browser remember you without a database.",
  "Static pages can still store user preferences in cookies.",
  "JavaScript can validate forms and show dynamic page content.",
  "A small site can work entirely in HTML, CSS, and JS."
];

const $ = id => document.getElementById(id);

let i = 0,
    id = getCookie("visitorId") || newId();

function show() {
  if (!$("articleTitle")) return;
  const article = articles[i];
  $("articleTitle").textContent = article[0];
  $("articleSummary").textContent = article[1];
}

function newId() {
  const value = Math.floor(Math.random() * 90000) + 10000;
  setCookie("visitorId", value, 7);
  return value;
}

function setCookie(name, value, days) {
  const expires = new Date(Date.now() + days * 864e5).toUTCString();
  document.cookie = `${name}=${encodeURIComponent(value)};expires=${expires};path=/`;
}

function getCookie(name) {
  return (
    document.cookie
      .split(";")
      .map(cookie => cookie.trim())
      .find(cookie => cookie.startsWith(name + "="))
      ?.split("=")
      .slice(1)
      .join("=") || ""
  );
}

function info() {
  if (!$("welcomeText")) return;
  const visitorName = getCookie("visitorName");
  $("welcomeText").textContent = visitorName
    ? `Welcome back, ${decodeURIComponent(visitorName)}! ID: ${id}`
    : "Welcome! Your data will appear here!";
}

function displayCookies() {
  if (!$("cookieDisplay")) return;
  const raw = document.cookie || "No cookies set.";
  $("cookieDisplay").textContent = raw;
  alert(`Raw cookie data:\n${raw}\n`);
}

function clearCookies() {
  if (!confirm("Clear all cookies? This cannot be undone.")) return;
  if (!document.cookie) {
    alert("No cookies to clear.");
    return;
  }
  document.cookie.split(';').forEach(cookie => {
    const eqPos = cookie.indexOf('=');
    const name = eqPos > -1 ? cookie.substr(0, eqPos).trim() : cookie.trim();
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/';
  });
  if ($("cookieDisplay")) $("cookieDisplay").textContent = 'No cookies set.';
  alert('All cookies cleared.');
  info();
}

function showRandomTip() {
  if (!$("aboutTip")) return;
  const tip = pageTips[Math.floor(Math.random() * pageTips.length)];
  $("aboutTip").textContent = tip;
}

function autoNext() {
  i = (i + 1) % articles.length;
  show();
}

$("prevBtn")?.addEventListener("click", () => {
  i = (i + articles.length - 1) % articles.length;
  show();
});

$("nextBtn")?.addEventListener("click", () => {
  i = (i + 1) % articles.length;
  show();
});

$("showCookiesBtn")?.addEventListener("click", displayCookies);

$("clearCookiesBtn")?.addEventListener("click", clearCookies);

$("contactForm")?.addEventListener("submit", e => {
  e.preventDefault();

  const name = $("name").value.trim();
  const email = $("email").value.trim();
  const subject = $("subject").value.trim();
  const message = $("message").value.trim();

  if (!name || !email || !subject || !message) {
    alert("Please fill in all fields.");
    return;
  }

  if (!/^\S+@\S+\.\S+$/.test(email)) {
    alert("Enter a valid email address.");
    return;
  }

  if (confirm("Send your message now?")) {
    setCookie("visitorName", name, 7);
    setCookie("visitorEmail", email, 7);
    setCookie("visitorTopic", subject, 7);
    setCookie("contactMessage", message, 7);
    $("contactResult").textContent = `Message sent! Thank you, ${name}.`;
    $("contactForm").reset();
    info();
  }
});

$("registerForm")?.addEventListener("submit", e => {
  e.preventDefault();

  const name = $("regName").value.trim();
  const email = $("regEmail").value.trim();
  const topic = $("favoriteTopic").value.trim();
  const terms = $("terms").checked;

  if (!name || !email || !topic) {
    alert("Please fill in all registration fields.");
    return;
  }

  if (!terms) {
    alert("Please agree to store your info in cookies.");
    return;
  }

  if (!/^\S+@\S+\.\S+$/.test(email)) {
    alert("Enter a valid email address.");
    return;
  }

  setCookie("visitorName", name, 7);
  setCookie("visitorEmail", email, 7);
  setCookie("favoriteTopic", topic, 7);
  setCookie("registered", "yes", 7);

  $("registerResult").textContent = `Saved! Visitor ID: ${id}`;
  info();
});

$("refreshTipBtn")?.addEventListener("click", showRandomTip);

show();
info();
showRandomTip();
setInterval(autoNext, 5000);