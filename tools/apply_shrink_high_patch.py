from pathlib import Path

root = Path('/home/ubuntu/work/Shrink_high')
user_config = root / 'TMessagesProj/src/main/java/org/telegram/messenger/UserConfig.java'
text = user_config.read_text()
old = 'public final static int MAX_ACCOUNT_COUNT = 4;'
new = 'public final static int MAX_ACCOUNT_COUNT = 100;'
if old not in text:
    raise SystemExit('UserConfig account declaration not found')
user_config.write_text(text.replace(old, new, 1))

loader = root / 'TMessagesProj/src/main/java/org/telegram/messenger/FileLoadOperation.java'
text = loader.read_text()
marker = 'public class FileLoadOperation {'
if marker not in text:
    raise SystemExit('FileLoadOperation class declaration not found')
insert = '''public class FileLoadOperation {\n    /** Bounded worker target for chunk scheduling; callers still honor device/network limits. */\n    public static final int SHRINK_HIGH_DOWNLOAD_WORKERS = 8;'''
text = text.replace(marker, insert, 1)
loader.write_text(text)
