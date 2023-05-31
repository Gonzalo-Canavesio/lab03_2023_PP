MAIN = FeedReaderMain
TARGETDIR = target
TARGET = $(TARGETDIR)/my-app-1.0-SNAPSHOT.jar

# Default target
default: run

# Target to compile the Java source files
build:
	mvn package

# Target to run the application
run: build
	spark-submit --class $(MAIN) $(TARGET)

clean:
	mvn clean

.PHONY: default build clean
