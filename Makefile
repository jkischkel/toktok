GRADLE	:= gradle

all:
	$(GRADLE) clean test

run:
	$(GRADLE) run
