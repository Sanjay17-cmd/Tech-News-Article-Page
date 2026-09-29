<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8"/>

<xsl:template match="/feedbacks">
<html>
<head>
<title>Feedback Summary (XSLT)</title>
<style>
body { font-family:'Segoe UI',Tahoma,sans-serif; background:#f4f6f8; margin:30px; }
.container { max-width:850px; margin:auto; background:white; padding:25px; border-radius:8px; box-shadow:0 2px 8px #ccc; }
table { width:100%; border-collapse:collapse; margin-top:15px; }
table, th, td { border:1px solid #ddd; }
th, td { padding:10px; text-align:left; }
th { background:#0066cc; color:white; }
tr:nth-child(even) { background:#f9f9f9; }
a { color:#0066cc; }
</style>
</head>
<body>
<div class="container">
<h2>Feedback Summary</h2>
<p>Generated from feedbacks.xml via XSLT. Total feedbacks:
   <b><xsl:value-of select="count(feedback)"/></b></p>
<table>
<tr><th>Name</th><th>Email</th><th>Subject</th><th>Message</th><th>Rating</th></tr>
<xsl:for-each select="feedback">
<tr>
<td><xsl:value-of select="name"/></td>
<td><xsl:value-of select="email"/></td>
<td><xsl:value-of select="subject"/></td>
<td><xsl:value-of select="message"/></td>
<td><xsl:value-of select="rating"/> / 5</td>
</tr>
</xsl:for-each>
</table>
<p><a href="feedbacks">Back to Feedback List</a></p>
</div>
</body>
</html>
</xsl:template>

</xsl:stylesheet>
