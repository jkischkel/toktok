GRADLE	:= gradle

all:
	$(GRADLE) clean test jar

run:
	$(GRADLE) run
