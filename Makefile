CC = clang
CFLAGS += -Wall -Wpedantic -Wwrite-strings -Ofast -Iinclude/
LFLAGS += -ldiscord -lcurl

SRC = $(wildcard src/*.c) $(wildcard src/**/*.c)
OBJ = $(SRC:.c=.o)

all: compile run

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

compile: $(OBJ)
	$(CC) $(CFLAGS) $(LFLAGS) -o bot $(OBJ)

run: bot
	./$<
