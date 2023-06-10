# Compiler options
JAVAC = javac
JFLAGS = -cp

# Source directory
SRCDIR = src

# Output directory
OUTDIR = out

# Java source files
SOURCES = $(shell find src -name "*.java")

# Main class
MAIN = FeedReaderMain

# Spark JARs directory
JARS_DIR = lib

ARGS = -ne

# Default target
default: run

# Target to compile the Java source files
build:
	$(JAVAC) $(JFLAGS) "$(OUTDIR):$(JARS_DIR)/*" -d $(OUTDIR) $(SOURCES)

# Target to run the application
run: build
	java $(JFLAGS) "$(OUTDIR):$(JARS_DIR)/*" $(MAIN)

run_args: build
	java $(JFLAGS) "$(OUTDIR):$(JARS_DIR)/*" $(MAIN) $(ARGS)

# Target to clean the project
clean:
	rm -rf $(OUTDIR)

.PHONY: default build clean
