# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.16

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Users\Oladushek\Programs\CLion 2020.1\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Users\Oladushek\Programs\CLion 2020.1\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "C:\Users\Oladushek\Desktop\Std\OO PO\Task1"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/Task1.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Task1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Task1.dir/flags.make

CMakeFiles/Task1.dir/main.cpp.obj: CMakeFiles/Task1.dir/flags.make
CMakeFiles/Task1.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Task1.dir/main.cpp.obj"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Task1.dir\main.cpp.obj -c "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\main.cpp"

CMakeFiles/Task1.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Task1.dir/main.cpp.i"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\main.cpp" > CMakeFiles\Task1.dir\main.cpp.i

CMakeFiles/Task1.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Task1.dir/main.cpp.s"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\main.cpp" -o CMakeFiles\Task1.dir\main.cpp.s

CMakeFiles/Task1.dir/LongInt.cpp.obj: CMakeFiles/Task1.dir/flags.make
CMakeFiles/Task1.dir/LongInt.cpp.obj: ../LongInt.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Task1.dir/LongInt.cpp.obj"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Task1.dir\LongInt.cpp.obj -c "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\LongInt.cpp"

CMakeFiles/Task1.dir/LongInt.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Task1.dir/LongInt.cpp.i"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\LongInt.cpp" > CMakeFiles\Task1.dir\LongInt.cpp.i

CMakeFiles/Task1.dir/LongInt.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Task1.dir/LongInt.cpp.s"
	C:\Users\OLADUS~1\Programs\CLION2~1.1\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\LongInt.cpp" -o CMakeFiles\Task1.dir\LongInt.cpp.s

# Object files for target Task1
Task1_OBJECTS = \
"CMakeFiles/Task1.dir/main.cpp.obj" \
"CMakeFiles/Task1.dir/LongInt.cpp.obj"

# External object files for target Task1
Task1_EXTERNAL_OBJECTS =

Task1.exe: CMakeFiles/Task1.dir/main.cpp.obj
Task1.exe: CMakeFiles/Task1.dir/LongInt.cpp.obj
Task1.exe: CMakeFiles/Task1.dir/build.make
Task1.exe: CMakeFiles/Task1.dir/linklibs.rsp
Task1.exe: CMakeFiles/Task1.dir/objects1.rsp
Task1.exe: CMakeFiles/Task1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable Task1.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Task1.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Task1.dir/build: Task1.exe

.PHONY : CMakeFiles/Task1.dir/build

CMakeFiles/Task1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Task1.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Task1.dir/clean

CMakeFiles/Task1.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" "C:\Users\Oladushek\Desktop\Std\OO PO\Task1" "C:\Users\Oladushek\Desktop\Std\OO PO\Task1" "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug" "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug" "C:\Users\Oladushek\Desktop\Std\OO PO\Task1\cmake-build-debug\CMakeFiles\Task1.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/Task1.dir/depend

