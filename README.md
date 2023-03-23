## Music bot only version can be found at: https://github.com/Lukires/dikubot/tree/version/musicbot
# (Discontinued version) dikubot

This bot is the Discord bot of The Department of Computer Science at The University of Copenhagen, also known as "DIKU"'s unofficial Discord Server

The bot's core features consist of university email confirmation and an internal support ticket system. It also contains a bunch of miscellaneous and fun features. Everyone is allowed to contribute to the bot, either by completing existing issues or by creating their own issues.

The bot is programmed in Java using JDA, a Java Discord Api (https://github.com/DV8FromTheWorld/JDA), as Java is an easy to learn language, meaning lesser experienced students at DIKU will be able to contribute.

## How to run

If you wish to run the bot, first you must replace the API keys found in the src/resources directory, with your own.
- src/resources/apitoken.txt must contain a Discord Bot API Token
- src/resources/sendgrindtoken.txt must contain a SendGrid API token (https://app.sendgrid.com)

You can simply call the **./execute.sh** script (on Linux) after you've added the tokens, or run the program through your IDE.

## Documentation

We're working on proper documentation, for now we just have our Javadocs at https://lukires.github.io/dikubot/
