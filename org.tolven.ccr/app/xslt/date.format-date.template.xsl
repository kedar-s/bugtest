<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:date="http://exslt.org/dates-and-times" xmlns:str="http://exslt.org/strings" extension-element-prefixes="date str">

<xsl:import href="property:org.tolven.xslt.ccr.str.padding"/>

<date:months>
   <date:month length="31" abbr="Jan">January</date:month>
   <date:month length="28" abbr="Feb">February</date:month>
   <date:month length="31" abbr="Mar">March</date:month>
   <date:month length="30" abbr="Apr">April</date:month>
   <date:month length="31" abbr="May">May</date:month>
   <date:month length="30" abbr="Jun">June</date:month>
   <date:month length="31" abbr="Jul">July</date:month>
   <date:month length="31" abbr="Aug">August</date:month>
   <date:month length="30" abbr="Sep">September</date:month>
   <date:month length="31" abbr="Oct">October</date:month>
   <date:month length="30" abbr="Nov">November</date:month>
   <date:month length="31" abbr="Dec">December</date:month>
</date:months>

<date:days>
   <date:day abbr="Sun">Sunday</date:day>
   <date:day abbr="Mon">Monday</date:day>
   <date:day abbr="Tue">Tuesday</date:day>
   <date:day abbr="Wed">Wednesday</date:day>
   <date:day abbr="Thu">Thursday</date:day>
   <date:day abbr="Fri">Friday</date:day>
   <date:day abbr="Sat">Saturday</date:day>
</date:days>

<xsl:template name="date:format-date"><xsl:param name="date-time"/><xsl:param name="pattern"/>
<xsl:value-of select="concat(substring($date-time, 6,2), '/', substring($date-time, 9,2), '/', substring($date-time, 1,4), ' at ', substring-after(substring-before($date-time, 'Z'), 'T'))" />


</xsl:template>

<xsl:template name="date:_format-date">
   <xsl:param name="year"/>
   <xsl:param name="month" select="1"/>
   <xsl:param name="day" select="1"/>
   <xsl:param name="hour" select="0"/>
   <xsl:param name="minute" select="0"/>
   <xsl:param name="second" select="0"/>
   <xsl:param name="timezone" select="'Z'"/>
   <xsl:param name="pattern" select="''"/>
   <xsl:variable name="char" select="substring($pattern, 1, 1)"/>
   <xsl:choose>
      <xsl:when test="not($pattern)"/>
      <xsl:when test="$char = &quot;'&quot;">
         <xsl:choose>
            <xsl:when test="substring($pattern, 2, 1) = &quot;'&quot;">
               <xsl:text>'</xsl:text>
               <xsl:call-template name="date:_format-date">
                  <xsl:with-param name="year" select="$year"/>
                  <xsl:with-param name="month" select="$month"/>
                  <xsl:with-param name="day" select="$day"/>
                  <xsl:with-param name="hour" select="$hour"/>
                  <xsl:with-param name="minute" select="$minute"/>
                  <xsl:with-param name="second" select="$second"/>
                  <xsl:with-param name="timezone" select="$timezone"/>
                  <xsl:with-param name="pattern" select="substring($pattern, 3)"/>
               </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
               <xsl:variable name="literal-value" select="substring-before(substring($pattern, 2), &quot;'&quot;)"/>
               <xsl:value-of select="$literal-value"/>
               <xsl:call-template name="date:_format-date">
                  <xsl:with-param name="year" select="$year"/>
                  <xsl:with-param name="month" select="$month"/>
                  <xsl:with-param name="day" select="$day"/>
                  <xsl:with-param name="hour" select="$hour"/>
                  <xsl:with-param name="minute" select="$minute"/>
                  <xsl:with-param name="second" select="$second"/>
                  <xsl:with-param name="timezone" select="$timezone"/>
                  <xsl:with-param name="pattern" select="substring($pattern, string-length($literal-value) + 2)"/>
               </xsl:call-template>
            </xsl:otherwise>
         </xsl:choose>
      </xsl:when>
      <xsl:when test="not(contains('abcdefghjiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', $char))">
         <xsl:value-of select="$char"/>
         <xsl:call-template name="date:_format-date">
            <xsl:with-param name="year" select="$year"/>
            <xsl:with-param name="month" select="$month"/>
            <xsl:with-param name="day" select="$day"/>
            <xsl:with-param name="hour" select="$hour"/>
            <xsl:with-param name="minute" select="$minute"/>
            <xsl:with-param name="second" select="$second"/>
            <xsl:with-param name="timezone" select="$timezone"/>
            <xsl:with-param name="pattern" select="substring($pattern, 2)"/>
         </xsl:call-template>
      </xsl:when>
      <xsl:when test="not(contains('GyMdhHmsSEDFwWakKz', $char))">
         <xsl:message>
            Invalid token in format string: <xsl:value-of select="$char"/>
         </xsl:message>
         <xsl:call-template name="date:_format-date">
            <xsl:with-param name="year" select="$year"/>
            <xsl:with-param name="month" select="$month"/>
            <xsl:with-param name="day" select="$day"/>
            <xsl:with-param name="hour" select="$hour"/>
            <xsl:with-param name="minute" select="$minute"/>
            <xsl:with-param name="second" select="$second"/>
            <xsl:with-param name="timezone" select="$timezone"/>
            <xsl:with-param name="pattern" select="substring($pattern, 2)"/>
         </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
         <xsl:variable name="next-different-char" select="substring(translate($pattern, $char, ''), 1, 1)"/>
         <xsl:variable name="pattern-length">
            <xsl:choose>
               <xsl:when test="$next-different-char">
                  <xsl:value-of select="string-length(substring-before($pattern, $next-different-char))"/>
               </xsl:when>
               <xsl:otherwise>
                  <xsl:value-of select="string-length($pattern)"/>
               </xsl:otherwise>
            </xsl:choose>
         </xsl:variable>
         <xsl:choose>
            <xsl:when test="$char = 'G'">
               <xsl:choose>
                  <xsl:when test="string($year) = 'NaN'"/>
                  <xsl:when test="$year &gt; 0">AD</xsl:when>
                  <xsl:otherwise>BC</xsl:otherwise>
               </xsl:choose>
            </xsl:when>
            <xsl:when test="$char = 'M'">
               <xsl:choose>
                  <xsl:when test="string($month) = 'NaN'"/>
                  <xsl:when test="$pattern-length &gt;= 3">
                     <xsl:variable name="month-node" select="document('')/*/date:months/date:month[number($month)]"/>
                     <xsl:choose>
                        <xsl:when test="$pattern-length &gt;= 4">
                           <xsl:value-of select="$month-node"/>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="$month-node/@abbr"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$pattern-length = 2">
                     <xsl:value-of select="format-number($month, '00')"/>
                  </xsl:when>
                  <xsl:otherwise>
                     <xsl:value-of select="$month"/>
                  </xsl:otherwise>
               </xsl:choose>
            </xsl:when>
            <xsl:when test="$char = 'E'">
               <xsl:choose>
                  <xsl:when test="string($year) = 'NaN' or string($month) = 'NaN' or string($day) = 'NaN'"/>
                  <xsl:otherwise>
                     <xsl:variable name="month-days" select="sum(document('')/*/date:months/date:month[position() &lt; $month]/@length)"/>
                     <xsl:variable name="days" select="$month-days + $day + boolean(((not($year mod 4) and $year mod 100) or not($year mod 400)) and $month &gt; 2)"/>
                     <xsl:variable name="y-1" select="$year - 1"/>
                     <xsl:variable name="dow" select="(($y-1 + floor($y-1 div 4) -                                              floor($y-1 div 100) + floor($y-1 div 400) +                                              $days)                                              mod 7) + 1"/>
                     <xsl:variable name="day-node" select="document('')/*/date:days/date:day[number($dow)]"/>
                     <xsl:choose>
                        <xsl:when test="$pattern-length &gt;= 4">
                           <xsl:value-of select="$day-node"/>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="$day-node/@abbr"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:otherwise>
               </xsl:choose>
            </xsl:when>
            <xsl:when test="$char = 'a'">
               <xsl:choose>
                  <xsl:when test="string($hour) = 'NaN'"/>
                  <xsl:when test="$hour &gt;= 12">PM</xsl:when>
                  <xsl:otherwise>AM</xsl:otherwise>
               </xsl:choose>
            </xsl:when>
            <xsl:when test="$char = 'z'">
               <xsl:choose>
                  <xsl:when test="$timezone = 'Z'">UTC</xsl:when>
                  <xsl:otherwise>UTC<xsl:value-of select="$timezone"/></xsl:otherwise>
               </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
               <xsl:variable name="padding">
                  <xsl:choose>
                     <xsl:when test="$pattern-length &gt; 10">
                        <xsl:call-template name="str:padding">
                           <xsl:with-param name="length" select="$pattern-length"/>
                           <xsl:with-param name="chars" select="'0'"/>
                        </xsl:call-template>
                     </xsl:when>
                     <xsl:otherwise>
                        <xsl:value-of select="substring('0000000000', 1, $pattern-length)"/>
                     </xsl:otherwise>
                  </xsl:choose>
               </xsl:variable>
               <xsl:choose>
                  <xsl:when test="$char = 'y'">
                     <xsl:choose>
                        <xsl:when test="string($year) = 'NaN'"/>
                        <xsl:when test="$pattern-length &gt; 2"><xsl:value-of select="format-number($year, $padding)"/></xsl:when>
                        <xsl:otherwise><xsl:value-of select="format-number(substring($year, string-length($year) - 1), $padding)"/></xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'd'">
                     <xsl:choose>
                        <xsl:when test="string($day) = 'NaN'"/>
                        <xsl:otherwise><xsl:value-of select="format-number($day, $padding)"/></xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'h'">
                     <xsl:variable name="h" select="$hour mod 12"/>
                     <xsl:choose>
                        <xsl:when test="string($hour) = 'NaN'"/>
                        <xsl:when test="$h"><xsl:value-of select="format-number($h, $padding)"/></xsl:when>
                        <xsl:otherwise><xsl:value-of select="format-number(12, $padding)"/></xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'H'">
                     <xsl:choose>
                        <xsl:when test="string($hour) = 'NaN'"/>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number($hour, $padding)"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'k'">
                     <xsl:choose>
                        <xsl:when test="string($hour) = 'NaN'"/>
                        <xsl:when test="$hour"><xsl:value-of select="format-number($hour, $padding)"/></xsl:when>
                        <xsl:otherwise><xsl:value-of select="format-number(24, $padding)"/></xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'K'">
                     <xsl:choose>
                        <xsl:when test="string($hour) = 'NaN'"/>
                        <xsl:otherwise><xsl:value-of select="format-number($hour mod 12, $padding)"/></xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'm'">
                     <xsl:choose>
                        <xsl:when test="string($minute) = 'NaN'"/>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number($minute, $padding)"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 's'">
                     <xsl:choose>
                        <xsl:when test="string($second) = 'NaN'"/>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number($second, $padding)"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'S'">
                     <xsl:choose>
                        <xsl:when test="string($second) = 'NaN'"/>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(substring-after($second, '.'), $padding)"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="$char = 'F'">
                     <xsl:choose>
                        <xsl:when test="string($day) = 'NaN'"/>
                        <xsl:otherwise>
                           <xsl:value-of select="floor($day div 7) + 1"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:when>
                  <xsl:when test="string($year) = 'NaN' or string($month) = 'NaN' or string($day) = 'NaN'"/>
                  <xsl:otherwise>
                     <xsl:variable name="month-days" select="sum(document('')/*/date:months/date:month[position() &lt; $month]/@length)"/>
                     <xsl:variable name="days" select="$month-days + $day + boolean(((not($year mod 4) and $year mod 100) or not($year mod 400)) and $month &gt; 2)"/>
                     <xsl:choose>
                        <xsl:when test="$char = 'D'">
                           <xsl:value-of select="format-number($days, $padding)"/>
                        </xsl:when>
                        <xsl:when test="$char = 'w'">
                           <xsl:call-template name="date:_week-in-year">
                              <xsl:with-param name="days" select="$days"/>
                              <xsl:with-param name="year" select="$year"/>
                           </xsl:call-template>
                        </xsl:when>
                        <xsl:when test="$char = 'W'">
                           <xsl:variable name="y-1" select="$year - 1"/>
                           <xsl:variable name="day-of-week" select="(($y-1 + floor($y-1 div 4) -                                                   floor($y-1 div 100) + floor($y-1 div 400) +                                                   $days)                                                    mod 7) + 1"/>
                           <xsl:choose>
                              <xsl:when test="($day - $day-of-week) mod 7">
                                 <xsl:value-of select="floor(($day - $day-of-week) div 7) + 2"/>
                              </xsl:when>
                              <xsl:otherwise>
                                 <xsl:value-of select="floor(($day - $day-of-week) div 7) + 1"/>
                              </xsl:otherwise>
                           </xsl:choose>
                        </xsl:when>
                     </xsl:choose>
                  </xsl:otherwise>
               </xsl:choose>
            </xsl:otherwise>
         </xsl:choose>
         <xsl:call-template name="date:_format-date">
            <xsl:with-param name="year" select="$year"/>
            <xsl:with-param name="month" select="$month"/>
            <xsl:with-param name="day" select="$day"/>
            <xsl:with-param name="hour" select="$hour"/>
            <xsl:with-param name="minute" select="$minute"/>
            <xsl:with-param name="second" select="$second"/>
            <xsl:with-param name="timezone" select="$timezone"/>
            <xsl:with-param name="pattern" select="substring($pattern, $pattern-length + 1)"/>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="date:_week-in-year">
   <xsl:param name="days"/>
   <xsl:param name="year"/>
   <xsl:variable name="y-1" select="$year - 1"/>
   <!-- this gives the day of the week, counting from Sunday = 0 -->
   <xsl:variable name="day-of-week" select="($y-1 + floor($y-1 div 4) -                           floor($y-1 div 100) + floor($y-1 div 400) +                           $days)                           mod 7"/>
   <!-- this gives the day of the week, counting from Monday = 1 -->
   <xsl:variable name="dow">
      <xsl:choose>
         <xsl:when test="$day-of-week"><xsl:value-of select="$day-of-week"/></xsl:when>
         <xsl:otherwise>7</xsl:otherwise>
      </xsl:choose>
   </xsl:variable>
   <xsl:variable name="start-day" select="($days - $dow + 7) mod 7"/>
   <xsl:variable name="week-number" select="floor(($days - $dow + 7) div 7)"/>
   <xsl:choose>
      <xsl:when test="$start-day &gt;= 4">
         <xsl:value-of select="$week-number + 1"/>
      </xsl:when>
      <xsl:otherwise>
         <xsl:choose>
            <xsl:when test="not($week-number)">
               <xsl:call-template name="date:_week-in-year">
                  <xsl:with-param name="days" select="365 + ((not($y-1 mod 4) and $y-1 mod 100) or not($y-1 mod 400))"/>
                  <xsl:with-param name="year" select="$y-1"/>
               </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
               <xsl:value-of select="$week-number"/>
            </xsl:otherwise>
         </xsl:choose>
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

</xsl:stylesheet>
<!-- Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios/><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->