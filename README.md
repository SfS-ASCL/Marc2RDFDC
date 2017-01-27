# MARC-21 to RDF-based Dublin Core conversion

Code Repository for metadata conversion from MARC-21 to RDF-based Dublin Core (RDF-DC)

For more information, please contact claus.zinn@uni-tuebingen.de

# Website

This web service allows you to convert MARC-21 format to (RDF-DC).

It makes use of a stylesheet retrieved from http://www.loc.gov/standards/marcxml/xslt

The conversion from MARC-21 to RDF-DC is currently being served at:

```http://weblicht.sfs.uni-tuebingen.de/converter/Marc2RDFDC/ ```

A REST interface is also available. See webpage above.

# Status

The software has no official release yet, but won't change much in the future, given the
stylesheet's fixed nature.  The software's design mirrors the one of the Marc2EAD software. The
diff is relatively small (different stylesheet, minor differences in website, and configuration
options).

In the future, we might want to offer a unifying website that offers users the various metadata
conversions they can perform.






