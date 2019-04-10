mkdir pdf graphviz

for i in *.gv;
  do name=`echo $i | cut -d'.' -f1`;
  echo $name;
  dot -Tpdf "$i" -o "${name}.pdf";
done

mv *.pdf pdf/
mv *.gv graphviz/
