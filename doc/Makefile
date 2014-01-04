FILES = Dokumention

all: $(FILES)

$(FILES):
	pdflatex $@
	bibtex $@
	pdflatex $@
	pdflatex $@

.PHONY: clean
clean:
	rm -rf $(FILES).aux $(FILES).bbl $(FILES).blg $(FILES).dvi $(FILES).log $(FILES).out $(FILES).toc
