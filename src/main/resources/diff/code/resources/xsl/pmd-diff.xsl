<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8"
		doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd" indent="yes" />
	<xsl:template match="DiffReport">
		<html>
			<head>
				<title>
					PMD
					<xsl:value-of select="//pmd/@version" />
					Report
				</title>
				<script type="text/javascript" src="sorttable.js"></script>
				<style type="text/css">
					body { margin-left: 2%; margin-right: 2%;
					font:normal
					verdana,arial,helvetica; color:#000000; }
					table.sortable
					tr th { font-weight: bold; text-align:left; background:#a6caf0; }
					table.sortable tr { background:#eeeee0; }
					table.classcount tr th
					{
					font-weight: bold; text-align:left; background:#a6caf0; }
					table.classcount tr td { background:#eeeee0; }
					table.summary tr th {
					font-weight: bold; text-align:left; background:#a6caf0; }
					table.summary tr td { background:#eeeee0; text-align:center;}
					.p1 {
					background:#FF9999; }
					.p2 { background:#FFCC66; }
					.p3 {
					background:#FFFF99; }
					.p4 { background:#99FF99; }
					.p5 {
					background:#9999FF; }
					div.top{text-align:right;margin:1em
					0;padding:0}
					div.top div{display:inline;white-space:nowrap}
					div.top
					div.left{float:left}
					#content>div.top{display:table;width:100%}
					#content>div.top div{display:table-cell}
					#content>div.top
					div.left{float:none;text-align:left}
					#content>div.top
					div.right{text-align:right}
		</style>
			</head>
			<body>
				<table border="0" class="summary">
					<tr>
						<th>Left:</th>
						<td>
							<xsl:value-of select="LeftBase/@value" />
						</td>
					</tr>
					<tr>
						<th>Right:</th>
						<td>
							<xsl:value-of select="RightBase/@value" />
						</td>
					</tr>
				</table>
				<table border="0" width="100%" id="sortable_id">
					<tr>
						<th>FileName</th>
						<th>P1</th>
						<th>P2</th>
						<th>P3</th>
						<th>P4</th>
						<th>P5</th>
					</tr>
					<xsl:for-each select="Files/File">
						<tr>
							<td>
								<xsl:value-of select="@name" />
							</td>
							<td>
								<xsl:value-of select="(number(Right/P1) - number(Left/P1))" />
							</td>
							<td>
								<xsl:value-of select="(number(Right/P2) - number(Left/P2))" />
							</td>
							<td>
								<xsl:value-of select="(number(Right/P3) - number(Left/P3))" />
							</td>
							<td>
								<xsl:value-of select="(number(Right/P4) - number(Left/P4))" />
							</td>
							<td>
								<xsl:value-of select="(number(Right/P5) - number(Left/P5))" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>